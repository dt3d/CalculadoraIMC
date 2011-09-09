package cti.br.dt3d.imccalc;

import java.text.DecimalFormat;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class IMCCalcActivity extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Cria uma instancia do botao calcular e aguarda ele ser pressionado
        View calcular = findViewById(R.id.calcular);
        calcular.setOnClickListener(this);
        //Botão sair
        View sair = findViewById(R.id.quit);
        sair.setOnClickListener(this);
        //Define acção ao clique na imagem
        View img = findViewById(R.id.logo1);
        img.setOnClickListener(this);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	//Quando o item "Sobre" do menu for pressionado, exibe a dialog box com as informações sobre a app
    	if(item.getItemId()==R.id.about){
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		builder.setMessage("Versão 1.1.0\nCentro de Tecnologia da Informação Renato Archer\n(www.cti.gov.br)\n\nAuthors:\nGuilherme H. P. da Silva\nGuilherme C. S. Ruppert\n");
    		builder.setCancelable(true);	    		
    		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
    		builder.show();
    		return true;
    	}
    	return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onClick(View v){
    	
    	//Sai da aplicação caso tenha sido apertado o botão sair
    	if(v.getId()==R.id.quit) finish();
    	//Caso tenha sido pressionado na Imagem
    	else if(v.getId()==R.id.logo1){
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		//Mostra a dialog box "Sobre"
    		builder.setMessage("Versão 1.1.0\nCentro de Tecnologia da Informação Renato Archer\n(www.cti.gov.br)\n\nAuthors:\nGuilherme H. P. da Silva\nGuilherme C. S. Ruppert\n");
    		builder.setCancelable(true);	    		
    		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
    		builder.show();
    	}
    	//Caso contrário
    	else{
    		AlertDialog.Builder builder = new AlertDialog.Builder(this);
    		//Lê os valores nas caixas de entrada de texto e as transforma em strings
    		EditText altura = (EditText) findViewById(R.id.altura);
	    	Editable altura1 = altura.getText();
	    	EditText massa = (EditText) findViewById(R.id.peso);
	    	Editable massa1 = massa.getText();
	    	String s_altura = altura1.toString();
	    	String s_massa = massa1.toString();
	    	String mensagem;
	    	DecimalFormat df = new DecimalFormat("0.0");
	    	float faltura, fmassa;
	    	//Tenta transformar as Strings em floats
	    	try{
		    	faltura = Float.parseFloat(s_altura);
		    	fmassa = Float.parseFloat(s_massa);
	    	}
	    	//Caso ocorra algum erro, cria uma dialog box informando ao usuário que os valores são inválidos
	    	catch(Exception e){
	    		builder.setMessage("Valores Inválidos.\nPor Favor, tente novamente!");
	    		builder.setCancelable(true);	    		
	    		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
	    		builder.show();
	    		return;
	    	}
	    	//Faz o calculo do IMC
	    	faltura=faltura/100;
	    	float imc = fmassa/(faltura*faltura);
	    	
	    	//Calcula os pesos máximos e mínimos para que a pessoa esteja na faixa Ideal 
	    	float idealmin = (float) (18.6*faltura*faltura);
	    	float idealmax = (float) (24.9*faltura*faltura);
	    	
	    	//Define a mensagem que será exibida na tela, dependendo do IMC calculado 
	    	if(imc<18.5)
	    		mensagem =("IMC: "+df.format(imc)+"\n\nClassificação:\nABAIXO DO PESO\n\nPeso Ideal:\nEntre "+df.format(idealmin)+" e "+df.format(idealmax));
	    	else if(imc>=18.5 && imc<25)
	    		mensagem = ("IMC: "+df.format(imc)+"\n\nClassificação:\nPESO IDEAL");
	       	else if(imc>=25 && imc<30)
	    		mensagem = ("IMC: "+df.format(imc)+"\n\nClassificação:\nACIMA DO PESO\n\nPeso Ideal:\nEntre "+df.format(idealmin)+" e "+df.format(idealmax));	    		
	    	else if(imc>=30 && imc<35)
	    		mensagem = ("IMC: "+df.format(imc)+"\n\nClassificação:\nOBESIDADE GRAU I\n\nPeso Ideal:\nEntre "+df.format(idealmin)+" e "+df.format(idealmax));	    		
	    	else if(imc>=35 && imc<40)
	    		mensagem = ("IMC: "+df.format(imc)+"\n\nClassificação:\nOBESIDADE GRAU II\n\nPeso Ideal:\nEntre "+df.format(idealmin)+" e "+df.format(idealmax));   		
	    	else
	    		mensagem = ("IMC: "+df.format(imc)+"\n\nClassificação:\nOBESIDADE GRAU III\n\nPeso Ideal:\nEntre "+df.format(idealmin)+" e "+df.format(idealmax));
	    	
	    	//Cria e exibe a dialog box com as informações
	    	builder.setMessage(mensagem);
    		builder.setCancelable(true);
    		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
					
				}
			});
    		builder.show();
    	}
    }
}