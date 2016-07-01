package com.the.harbor.service.interfaces;

import com.the.harbor.commons.redisdata.def.DoNotify;

public interface INotifySV {

	void process(DoNotify notify);

}
