package com.jpattern.gwt.client.serializer;

public class Bean1Holder implements IBean1Holder {

	private IBean bean1;
	private IBean bean2;
	private IBean bean3;

	@Override
	public IBean getBean1() {
		return bean1;
	}

	@Override
	public IBean getBean2() {
		return bean2;
	}

	@Override
	public IBean getBean3() {
		return bean3;
	}

	public void setBean1(IBean bean1) {
		this.bean1 = bean1;
	}

	public void setBean2(IBean bean2) {
		this.bean2 = bean2;
	}

	public void setBean3(IBean bean3) {
		this.bean3 = bean3;
	}
	
}
