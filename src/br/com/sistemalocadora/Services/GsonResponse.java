package br.com.sistemalocadora.Services;

import com.google.gson.Gson;

class GsonResponse {

	private static Gson json;

	private GsonResponse() {	
	}

	public static <T> String JsonBilder(T objeto) {
		json = new Gson();
		return json.toJson(objeto);
	}

}
