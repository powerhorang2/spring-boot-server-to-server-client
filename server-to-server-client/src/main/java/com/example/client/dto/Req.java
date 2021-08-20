package com.example.client.dto;

public class Req<T> {

	private Header header;

	private T resBody;

	public static class Header {

		private String responseCode;

		/**
		 * @return the responseCode
		 */
		public String getResponseCode() {
			return responseCode;
		}

		/**
		 * @param responseCode the responseCode to set
		 */
		public void setResponseCode(String responseCode) {
			this.responseCode = responseCode;
		}

		@Override
		public String toString() {
			return "Header [responseCode=" + responseCode + "]";
		}

	}

	/**
	 * @return the header
	 */
	public Header getHeader() {
		return header;
	}

	/**
	 * @param header the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
	}

	/**
	 * @return the resBody
	 */
	public T getResBody() {
		return resBody;
	}

	/**
	 * @param resBody the resBody to set
	 */
	public void setResBody(T resBody) {
		this.resBody = resBody;
	}

	@Override
	public String toString() {
		return "Req [header=" + header + ", resBody=" + resBody + "]";
	}

}
