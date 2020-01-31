package com.games.app.dto;

import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "error")
public class ExceptionResponse
{
 
    private String status;
    private String txnId;
    private String errorCode;
    private String errorDescription;
	public ExceptionResponse(String status, String txnId, String errorCode, String errorDescription) {
		super();
		this.status = status;
		this.txnId = txnId;
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}
	public ExceptionResponse() {
		
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [status=" + status + ", txnId=" + txnId + ", errorCode=" + errorCode
				+ ", errorDescription=" + errorDescription + "]";
	}
    
}