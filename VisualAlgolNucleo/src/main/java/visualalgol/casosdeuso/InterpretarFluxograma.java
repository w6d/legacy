package visualalgol.casosdeuso;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import visualalgol.casosdeuso.comandos.InterpretadorMediador;
import visualalgol.entidades.Algoritmo;
import visualalgol.entidades.Comando;
import visualalgol.entidades.CondicaoFim;
import visualalgol.entidades.CondicaoIf;
import visualalgol.entidades.Inicio;
import visualalgol.entidades.InstrucaoGenerica;
import visualalgol.entidades.Linha;

public class InterpretarFluxograma extends CasoDeUso{
	private static Logger logger = Logger.getLogger(InterpretarFluxograma.class);
	
	/**
	 * Somente para encapsular a chamada ao metodo
	 * @param pseudoCodigo string em portugol
	 * @return condicao tratada para funcionar em C like
	 */
	public static String tratarStringDeCondicao(String pseudoCodigo){
		if(pseudoCodigo==null) return "";
		//tratamento basico de "e" e "ou"
		pseudoCodigo = pseudoCodigo
			.replace(" e ", " && ")
			.replace(" ou ", " || ");
		String retorno="";
		int nAspa=0;
		boolean ultimaBarra=false;
		boolean ultimoCharMaiorMenorQue=false;
		for(int i=0;i<pseudoCodigo.length();i++){
			char c = pseudoCodigo.charAt(i);
			retorno+=c;
			boolean par = nAspa%2==0;
			if(c=='"' && par){
				nAspa++;
			}else{
				if(par){
					if(c=='=' && !ultimoCharMaiorMenorQue){
						//se o proximo nao for igual, colocamos o proximo como igual
						if((i+1)<pseudoCodigo.length()){
							if(pseudoCodigo.charAt(i+1)=='='){
								i++;
							}
							retorno+='=';
						}
					}
				}else{//impar
					if(!ultimaBarra && c=='"'){
						nAspa++;
					}
				}
				
				//no final ver se eh barra
				ultimaBarra = c=='\\';
				ultimoCharMaiorMenorQue=(c=='>' || c=='<');
			}
		}
		return retorno;
	}
	
	@Override
	public void executarComoThread() throws InterruptedException {
		Algoritmo alg = sistema.getAlgoritmo();
		interpretarAlgoritmo(alg);
	}
	
	/**
	 * Importante notar que nao existe instanceof While
	 * @param alg
	 */
	public synchronized void interpretarAlgoritmo(Algoritmo alg){
		//zerar os executados
		for(InstrucaoGenerica instrucao: alg.getListComando()){
			instrucao.setExecutado(false);
			instrucao.getVariaveis().clear();
		}
		for (Linha linha : alg.getListLinha()) {
			linha.setExecutado(false);
		}
		
		Inicio inicio = alg.getComandoInicial();
		
		// Creates and enters a Context. The Context stores information
        // about the execution environment of a script.
        Context cx = Context.enter();
        cx.setDebugger(null, null);
        try {
            // Initialize the standard objects (Object, Function, etc.)
            // This must be done before scripts can be executed. Returns
            // a scope object that we use in later calls.
            Scriptable scope = cx.initStandardObjects();
            //Iniciar log
            InterpretadorMediador.getInstance().interpretadorFluxogramaIniciado();
            // Collect the arguments into a single string.
            //load
    		InstrucaoGenerica instrucao = inicio.getLinhaSaida().getDestino();
    		instrucao.setInstrucaoAnterior(inicio);
    		while(instrucao!=null){
    			sistema.apontarPara(instrucao);
    			if(instrucao instanceof Comando){
    				Comando comando = (Comando) instrucao;
    				String s = comando.getPseudoCodigo();
    				
    				// Now evaluate the string we've colected.
    				try{
    					if(s.startsWith("leia ")){
        					s = s.substring(5);
        					String vars[] = s.split(",");
        					for(int i=0;i<vars.length;i++){
        						String input = JOptionPane.showInputDialog("Informe um valor para " + vars[i]);
            					if(input==null){
            						JOptionPane.showMessageDialog(sistema.getComponent(),"Execucao cancelada");
            						return;
            					}
            					// verificar o tipo
            					if(input.matches("^-?[0-9]*,[0-9]+$")){
            						input = input.replace(",",".");
            					}else if(input.matches("^-?[0-9]*\\.[0-9]+$")){
            						//do nothing
            					}else if(input.matches("^-?[0-9]+$")){
            						input = input.replace(",",".");
            					}else{//tratar como string
            						input = "\"" + input.replace("\"", "\\\"")+"\"";
            					}
            					cx.evaluateString(scope,vars[i] + "=" + input, "<cmd>", 1, null);
        					}
        				}else if(s.startsWith("imprima ")){
    						Object result = cx.evaluateString(scope, s.substring(8), "<cmd>", 1, null);
        					JOptionPane.showMessageDialog(null,result);
        				}else{//just execute
        					cx.evaluateString(scope, s, "<cmd>", 1, null);
        				}
    					InterpretadorMediador.getInstance().informarComandoExecutado(comando, scope,s);
    				}catch(RuntimeException e){
    					logger.error("Erro ao interpretar comando s = '"+s+"'",e);
    					JOptionPane.showInputDialog(e.getMessage() + "?");
    					return;
    				}
    	            comando.setExecutado(true);
    	            //print chinezinho
    	            Object obj[] = scope.getIds();
    	    		for(int i=0;i<obj.length;i++){
    	    			String key = obj[i].toString();
    	    			Object value = scope.get(key,scope);
    	    			System.err.println(key + " <-- " + value);
    	    		}
    	            
    	            //load
        			instrucao = comando.getLinhaSaida().getDestino();
        			instrucao.setInstrucaoAnterior(comando);
        			comando.getLinhaSaida().setExecutado(true);
    			}else if(instrucao instanceof CondicaoIf){
    				CondicaoIf condicao = (CondicaoIf) instrucao;
    				String s = condicao.getPseudoCodigo();
    				try{
    					//tratar se escrever if(a=" ze = peekdk")
    					s = tratarStringDeCondicao(s);
	    				// Now evaluate the string we've colected.
	    	            Object result = cx.evaluateString(scope, s, "<cmd>", 1, null);
	    	            String resposta = Context.toString(result);
	    	            // Convert the result to a string and print it.
	    	            System.err.println(s +" -> "+ resposta);
	    	            condicao.setExecutado(true);
	    	            InterpretadorMediador.getInstance().informarComandoExecutado(condicao, scope, s);
	    	            if(resposta.equals("true")){
	    	            	condicao.setResultado(true);
	    	            	instrucao = condicao.getLinhaVerdadeira().getDestino();
	    	            	instrucao.setInstrucaoAnterior(condicao);
	    	            	condicao.getLinhaVerdadeira().setExecutado(true);
	    	            }else{
	    	            	condicao.setResultado(false);
	    	            	instrucao = condicao.getLinhaFalsa().getDestino();
	    	            	instrucao.setInstrucaoAnterior(condicao);
	    	            	condicao.getLinhaFalsa().setExecutado(true);
	    	            }
    				}catch(RuntimeException e){
    					logger.error("Erro ao interpretar if s = '"+s+"'",e);
    					JOptionPane.showMessageDialog(sistema.getComponent(),"Erro: " + e.getMessage());
    					return;
    				}
    			}else if(instrucao instanceof CondicaoFim){
    				CondicaoFim condicaoFim = (CondicaoFim) instrucao;
    				condicaoFim.setExecutado(true);
    				//load
        			instrucao = condicaoFim.getLinhaSaida().getDestino();
        			instrucao.setInstrucaoAnterior(condicaoFim);
        			condicaoFim.getLinhaSaida().setExecutado(true);
    			}else{
    				instrucao = null;
    			}
    		}//end the while
        } finally {
            // Exit from the context.
            Context.exit();
        }
        sistema.apontarPara(null);
	}
}
