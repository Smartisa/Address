package com.DY;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class getregion{
			 
	 /* 
		 * 
		 * 
		 * 
		 * 地址编码
		 * 
		 * */
	 public static String getLocation(String lat,String lng){
		   String location1="";
		  String url="http://api.map.baidu.com/reverse_geocoding/v3/?ak=ld0uqubVfSTAUlXH5qIMN2F3Snsp16LU&output=xml&coordtype=wgs84ll&location="+lat+","+lng;
		  System.out.println(url);
		  Document doc = null;
		   HttpURLConnection conn = null;
		   InputStream ins = null;
		   SAXReader reader = null;
		   try{
		    //HttpTimeoutHandler hth = new HttpTimeoutHandler(600000);
		    URL conURL = new URL(null,url);
		    conn = (HttpURLConnection)conURL.openConnection();
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    ins = conn.getInputStream();
		    reader =new SAXReader();
		    doc= reader.read(ins);
		    //System.out.println(url);
		    Element root=doc.getRootElement();
		    String docXmlText=doc.asXML();
		    //System.out.println(docXmlText);
		    Element e=root.element("result");
		    Element location=e.element("formatted_address");
		    location1=location.asXML();
		    location1=location1.substring(location1.indexOf("address>")+8,location1.indexOf("</formatted_address>"));
		   /* System.out.println("lng"+lng1);
		    System.out.println("lat"+lat1);
		   // System.out.println("location"+location.asXML());
		    //System.out.println("xiayukun"+e.asXML());
		    lng1=lng1.substring(lng1.indexOf("<lng>")+5,lng1.indexOf("</lng>"));
		    
		    System.out.println(lng1);
		    lat1=lat1.substring(lat1.indexOf("<lat>")+5,lat1.indexOf("</lat>"));
		    System.out.println(lat1);*/
		    List<Element> list = root.elements("location");
		    System.out.println(url);
		    for (Element object : list) {
		    	System.out.println(url);
		    	System.out.println(object.getName());
				for (Element element : (List<Element>) object.elements()) {
					System.out.print(((Element) element).getName() + ":");
					System.out.print(element.getText() + " ");
				}
				System.out.println();

			}

		    ins.close();
		    conn.disconnect();
		   }catch (MalformedURLException e) {
		    e.printStackTrace();
		   } catch (IOException e) {
		    e.printStackTrace();   
		   } catch (DocumentException e) {
		    e.printStackTrace();
		   }catch(Exception e){
		    e.printStackTrace();
		   }finally {
		    try {
		     if (ins != null) {
		      ins.close();
		      ins = null;
		     }
		    } catch (IOException e1) {
		     e1.printStackTrace();
		    }
		    try {
		     if (conn != null) {
		      conn.disconnect();
		      conn = null;
		     }
		    } catch (Exception e2) {
		     e2.printStackTrace();
		    }
		   }
		   return location1;
		}
	/* 
	 * 
	 * 
	 * 
	 * 地址你编码
	 * 
	 * */
	public static String getlocation1(String loc){
		String location2="";
		  String url="http://api.map.baidu.com/geocoding/v3/?address="+loc+"&output=xml&ak=ld0uqubVfSTAUlXH5qIMN2F3Snsp16LU&callback=showLocation";
		  System.out.println(url);
		  Document doc = null;
		   HttpURLConnection conn = null;
		   InputStream ins = null;
		   SAXReader reader = null;
		   try{
		    //HttpTimeoutHandler hth = new HttpTimeoutHandler(600000);
		    URL conURL = new URL(null,url);
		    conn = (HttpURLConnection)conURL.openConnection();
		    conn.setDoInput(true);
		    conn.setDoOutput(true);
		    conn.setUseCaches(false);
		    ins = conn.getInputStream();
		    reader =new SAXReader();
		    doc= reader.read(ins);
		    //System.out.println(url);
		    Element root=doc.getRootElement();
		    String docXmlText=doc.asXML();
		    //System.out.println(docXmlText);
		    Element e=root.element("result");
		    Element location=e.element("location");
		    Element lng=location.element("lng");
		    Element lat=location.element("lat");
		    String lng1=lng.asXML();
		    String lat1=lat.asXML();
		    System.out.println("lng"+lng1);
		    System.out.println("lat"+lat1);
		   // System.out.println("location"+location.asXML());
		    //System.out.println("xiayukun"+e.asXML());
		    lng1=lng1.substring(lng1.indexOf("<lng>")+5,lng1.indexOf("</lng>"));
		    
		    System.out.println(lng1);
		    lat1=lat1.substring(lat1.indexOf("<lat>")+5,lat1.indexOf("</lat>"));
		    System.out.println(lat1);
		    location2=getLocation(lat1,lng1);
		    List<Element> list = root.elements("location");
		    System.out.println(url);
		    for (Element object : list) {
		    	System.out.println(url);
		    	System.out.println(object.getName());
				for (Element element : (List<Element>) object.elements()) {
					System.out.print(((Element) element).getName() + ":");
					System.out.print(element.getText() + " ");
				}
				System.out.println();

			}

		    ins.close();
		    conn.disconnect();
		   }catch (MalformedURLException e) {
		    e.printStackTrace();
		   } catch (IOException e) {
		    e.printStackTrace();   
		   } catch (DocumentException e) {
		    e.printStackTrace();
		   }catch(Exception e){
		    e.printStackTrace();
		   }finally {
		    try {
		     if (ins != null) {
		      ins.close();
		      ins = null;
		     }
		    } catch (IOException e1) {
		     e1.printStackTrace();
		    }
		    try {
		     if (conn != null) {
		      conn.disconnect();
		      conn = null;
		     }
		    } catch (Exception e2) {
		     e2.printStackTrace();
		    }
		   }
		   return location2;
		}
	public static String SELECT(String name)
	{
		String result="";
		result=getlocation1(name);
		return result;
	}
	public static void main(String[] strgs){
		/*System.out.println("aaaa");
		String loc="石家庄新百广场";
		System.out.println(loc);
		String structloc="";
		structloc=getlocation1(loc);
		System.out.println("struct_location:"+structloc);
		*/
		System.out.println(SELECT("北京建工土木工程有限公司"));
		
		/*//System.out.println(addressResolution("湖北省武汉市洪山区"));
		//getPoint("河北省迁安市聚鑫街2126号");
		ArrayList<Map<String,String>> table=new ArrayList<Map<String,String>>();
		table = addressResolution(structloc);
		System.out.println(table.size());				
		for(int i = 0; i < table.size(); i++){
			System.out.println(table.get(i).get("province")+table.get(i).get("city")+table.get(i).get("county"));
		}*/
	}
	
	
	
}