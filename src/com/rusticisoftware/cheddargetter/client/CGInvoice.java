package com.rusticisoftware.cheddargetter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

public class CGInvoice {
	protected String id;
	protected String number;
	protected String type;
	protected Date billingDatetime;
	protected Date createdDatetime;
	protected List<CGTransaction> transactions;
	
	public String getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public Date getBillingDatetime() {
		return billingDatetime;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	
	public List<CGTransaction> getTransactions(){
		return transactions;
	}

	public CGInvoice(Element elem){
		this.id = elem.getAttribute("id");
		this.number = XmlUtils.getNamedElemValue(elem, "number");
		this.type = XmlUtils.getNamedElemValue(elem, "type");
		this.billingDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "billingDatetime"));
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		
		Element transactionsParent = XmlUtils.getFirstChildByTagName(elem, "transactions");
		if(transactionsParent != null){
			this.transactions = new ArrayList<CGTransaction>();
			List<Element> transactionsList = XmlUtils.getChildrenByTagName(transactionsParent, "transaction");
			for(Element transaction : transactionsList){
				this.transactions.add(new CGTransaction(transaction));
			}
		}
	}
}
