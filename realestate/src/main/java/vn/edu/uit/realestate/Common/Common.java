package vn.edu.uit.realestate.Common;

import org.springframework.beans.factory.annotation.Value;

public class Common {
	public static class Constains {
		public static String ROLE_ADMIN = "ADMIN";
		
		public static String ROLE_USER = "USER";

		public static String MONEY_UNIT = " VNĐ";

		@Value("${server.port}")
		private static String port;
		public static String DOMAIN = "http://localhost:" + port;
		
		public static final String GRAPH_MODEL_PACKAGE = "vn.edu.uit.realestate.Graph.Model";
		
		public static final String GRAPH_REPOSITORY_PACKAGE = "vn.edu.uit.realestate.Graph.Repository";
		
		public static final String RELATIONAL_MODEL_PACKAGE = "vn.edu.uit.realestate.Relational.Model";
		
		public static final String RELATIONAL_REPOSITORY_PACKAGE = "vn.edu.uit.realestate.Relational.Repository";
		
		public static final String MAIN_PACKAGE = "vn.edu.uit.realestate";
	}
}
