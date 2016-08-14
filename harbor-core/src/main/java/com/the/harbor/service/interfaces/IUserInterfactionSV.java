package com.the.harbor.service.interfaces;

import com.the.harbor.api.user.param.DoIMUserSync;

public interface IUserInterfactionSV {

	void process(String mnsBody);

	void userSync2OpenIM(DoIMUserSync notify);

}
