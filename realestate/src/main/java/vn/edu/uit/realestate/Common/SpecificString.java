package vn.edu.uit.realestate.Common;

public class SpecificString {
	private static SpecificString _instance;

	//
	public static SpecificString getInstance() {
		if (_instance == null) {
			_instance = new SpecificString();
		}
		return _instance;
	}

	public String email_is_existed = "This email has already existed!";

	public String successful_registration = "Register successfully! Please check your email to confirm";

	public String account_verified = "Account verified";
	
	public String check_email_to_confirm_changing_password = "Please check your email to confirm changing password";

	public String invalid_or_broken_link = "The link is invalid or broken!";
	
	public String new_password_has_been_updated = "Your new password has been updated";

	
	
	public String account_or_password_is_incorrect = "Account or password is incorrect!";

	public String could_not_send_verification_code = "Something is error, we could not send verification code for you!";

	public String wrong_verifcation_code = "Verification code is incorrect";

	public String confirm_password_is_not_match = "Comfirm password and password is not match!";

	public String reset_password_successful = "Reset password successful, you can login now";

	public String email_is_not_exists = "Your email was not registed in system";
}
