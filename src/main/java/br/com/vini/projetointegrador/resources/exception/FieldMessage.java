package br.com.vini.projetointegrador.resources.exception;

import java.io.Serializable;

public class FieldMessage  implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private String filedName;
		private String message;
		public FieldMessage(String filedName, String message) {
			super();
			this.filedName = filedName;
			this.message = message;
		}
		public String getFieldName() {
			return filedName;
		}
		public void setFieldName(String filedName) {
			this.filedName = filedName;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
		
		
}
