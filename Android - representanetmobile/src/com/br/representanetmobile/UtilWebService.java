package com.br.representanetmobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

public class UtilWebService 
{	
	public static Boolean loginRemotoOK(String usuario, String senha)
	{
		try
		{
			String resultado;
			String METHOD_NAME = "usuarioOk";
			String SOAP_ACTION= Util.NAMESPACE + METHOD_NAME; 
			
			SoapObject request = new SoapObject(Util.NAMESPACE,METHOD_NAME);
			request.addProperty("usuario", usuario);
			request.addProperty("senha", senha);
			 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; 
			 
			//envio o que eu quero, metodos e parametros
			envelope.setOutputSoapObject(request);
					 
			//Informo o endereço que deverei buscar
			HttpTransportSE transporte = new HttpTransportSE(Util.URL);		
			transporte.call(SOAP_ACTION, envelope);
			SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
			
			resultado = resultado_xml.toString();
			if (resultado.equalsIgnoreCase("OK!"))
				return true;			
		} 
		catch (IOException | XmlPullParserException e) 
		{
			e.printStackTrace();
		}				
		return false;
	}

	public static ArrayList<String> carregarDadosArrayList(String usuario, String senha, String METHOD_NAME)
	{
		ArrayList<String> lista = new ArrayList<String>();
		try 
		{
			String SOAP_ACTION= Util.NAMESPACE + METHOD_NAME; 
		
			SoapObject request = new SoapObject(Util.NAMESPACE,METHOD_NAME);
			request.addProperty("usuario", usuario);
			request.addProperty("senha", senha);
			 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; 
			 
			//envio o que eu quero, metodos e parametros
			envelope.setOutputSoapObject(request);
					 
			//Informo o endereço que deverei buscar
			HttpTransportSE transporte = new HttpTransportSE(Util.URL);

			transporte.call(SOAP_ACTION, envelope);
			Object response = envelope.getResponse();

			SoapObject soapObject = (SoapObject) response;
			for(int indice = 0; indice < soapObject.getPropertyCount(); indice ++)
				lista.add(soapObject.getProperty(indice).toString());
		} 
		catch (IOException | XmlPullParserException e) 
		{			
			lista.add("Não foi possivel carregar dados: " + e.getMessage());			
		}				
		
		return lista;
	}

	public static String enviarPedido(String usuario, String senha, String sqlCab, String sqlItem)
	{
		String resultado = "";		
		
		try 
		{  		
			String METHOD_NAME="incluirPedido";
			String SOAP_ACTION= Util.NAMESPACE + METHOD_NAME; 
			
			SoapObject request = new SoapObject(Util.NAMESPACE,METHOD_NAME);
			request.addProperty("usuario", usuario);
			request.addProperty("senha", senha);
			request.addProperty("sqlCab", sqlCab);
			request.addProperty("sqlItens", sqlItem);
			 
			SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			envelope.dotNet = true; 
			 
			//envio o que eu quero, metodos e parametros
			envelope.setOutputSoapObject(request);
					 
			//Informo o endereço que deverei buscar
			HttpTransportSE transporte = new HttpTransportSE(Util.URL);


			transporte.call(SOAP_ACTION, envelope);
			SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
			
			resultado = resultado_xml.toString();
			//lbTeste.setText(resultado);
		} 
		catch (IOException | XmlPullParserException e) 
		{
			// TODO Auto-generated catch block
			return "Não foi possivel enviar pedido: " + e.getMessage();
		}				
		return resultado;
	}

}
