package com;

import model.Inquiry;

//for REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//for JSON
import com.google.gson.*;

//for XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Inquiries")
public class inquiryService {
	
	Inquiry inquiryObj = new Inquiry();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readInquiries() {
		
		return inquiryObj.readInquiries();
		
		
		
		
	}
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_FORM_URLENCODED)
	public String insertInquiries(
							@FormParam("accountNum") String accountNum,
							@FormParam("Name") String Name,
							@FormParam("contactNum") String contactNum,
							@FormParam("email") String email,
							@FormParam("inquiryDet") String inquiryDet)
							
	{
		
		String output = inquiryObj.insertInquiry(accountNum, Name, contactNum, email, inquiryDet);
		return output;
		
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateInquiries(String inquiryData)
	{
		//Convert input string to a JSON object
		JsonObject inquiryObject = new JsonParser().parse(inquiryData).getAsJsonObject();
		
		//Read values from JSON object
		String inquiryID = inquiryObject.get("inquiryID").getAsString();
		String accountNum = inquiryObject.get("accountNum").getAsString();
		String Name = inquiryObject.get("Name").getAsString();
		String contactNum = inquiryObject.get("contactNum").getAsString();
		String email = inquiryObject.get("email").getAsString();
		String inquiryDet = inquiryObject.get("inquiryDet").getAsString();
		
		String output = inquiryObj.updateInquiry(inquiryID, accountNum, Name, contactNum, email, inquiryDet );
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteInquiries(String inquiryData)
	{
		//Convert input string to a JSON object
		Document doc = Jsoup.parse(inquiryData, "", Parser.xmlParser());
		
		//Read values from JSON object
		String inquiryID = doc.select("inquiryID").text();
		
		String output = inquiryObj.deleteInquiry(inquiryID);
		return output;
		
	}

}
