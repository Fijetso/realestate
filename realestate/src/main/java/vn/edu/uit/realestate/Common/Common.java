package vn.edu.uit.realestate.Common;

public class Common {
	public static class Constains {
		public static String ROLE_ADMIN = "ADMIN";
		
		public static String ROLE_USER = "USER";

		public static String MONEY_UNIT = " VNĐ";
		
		public static String LOCAL_DATE_FORMAT = "dd/MM/yyyy";

		public static String LOCAL_DATE_TIME_FORMAT = "dd/MM/yyyy hh:mm";

//		@Value("${local.server.port}")
		private static String port="8081";
		
		public static String DOMAIN = "http://localhost:" + port;
		
		public static final String GRAPH_MODEL_PACKAGE = "vn.edu.uit.realestate.Graph.Model";
		
		public static final String GRAPH_REPOSITORY_PACKAGE = "vn.edu.uit.realestate.Graph.Repository";
		
		public static final String RELATIONAL_MODEL_PACKAGE = "vn.edu.uit.realestate.Relational.Model";
		
		public static final String RELATIONAL_REPOSITORY_PACKAGE = "vn.edu.uit.realestate.Relational.Repository";
		
		public static final String MAIN_PACKAGE = "vn.edu.uit.realestate";
	}
}
