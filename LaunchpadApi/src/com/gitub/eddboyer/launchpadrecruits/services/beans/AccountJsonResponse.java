package com.gitub.eddboyer.launchpadrecruits.services.beans;

import com.gitub.eddboyer.launchpadrecruits.beans.LaunchpadAccountDetailsBean;

/**
 * Account json response.
 * <p>
 * Used by: Create Account, Show Account Details, and Update Account Details.
 * <p>
 * Example response (from Create):
 * {"response":{"id":425,"company_name":"Acme Rocket-Powered Products, Inc.","email":
 * "eboyer@workdocx.com"}}
 * 
 * @author Edd Boyer
 */
public class AccountJsonResponse extends LaunchpadJsonResponse<LaunchpadAccountDetailsBean> {
	// nuff said
}
