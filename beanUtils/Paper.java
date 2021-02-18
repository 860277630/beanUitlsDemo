package com.example.demo.entity;

public class Paper extends Books{

    private Double prices;

	//无参构造
	public Paper() {
		super();
	}

	//自定义的有参构造，super表示父类，this表示本类
	public Paper(Double pricesForThis,Integer id, String bookname, Double prices, Integer counts, Integer typeid) {
		super(id, bookname, prices, counts, typeid);
		// TODO Auto-generated constructor stub
		this.prices = pricesForThis;
	}
	
	public Double getPrices() {
		return prices;
	}

	public void setPrices(Double prices) {
		this.prices = prices;
	}

	
	
	@Override
	public String toString() {
		return "Paper [prices=" + prices + ", toString()=" + super.toString() + "]";
	}

	
	

	
	
    
    
}
