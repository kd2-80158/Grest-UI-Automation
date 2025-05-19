package com.ui.pojos;

public class CreateProduct {
	
	private String name;
	private String gsn;
	private String description;
	private int mrp;
	private int payprice;
	private int sgst;
	private int cgst;
	private int igst;
	private int stock;
	private String categoryLevel1;
	private String categoryLevel2;
	private String categoryLevel3;
		

	public CreateProduct(String name, String gsn, String description, int mrp, int payprice, int sgst, int cgst,
			int igst, int stock, String categoryLevel1, String categoryLevel2, String categoryLevel3) {
		super();
		this.name = name;
		this.gsn = gsn;
		this.description = description;
		this.mrp = mrp;
		this.payprice = payprice;
		this.sgst = sgst;
		this.cgst = cgst;
		this.igst = igst;
		this.stock = stock;
		this.categoryLevel1 = categoryLevel1;
		this.categoryLevel2 = categoryLevel2;
		this.categoryLevel3 = categoryLevel3;
	}

	public String getCategoryLevel1() {
		return categoryLevel1;
	}


	public String getCategoryLevel2() {
		return categoryLevel2;
	}


	public String getCategoryLevel3() {
		return categoryLevel3;
	}

	public String getName() {
		return name;
	}

	public String getGsn() {
		return gsn;
	}

	public String getDescription() {
		return description;
	}

	public int getMrp() {
		return mrp;
	}

	public int getPayprice() {
		return payprice;
	}

	public int getSgst() {
		return sgst;
	}

	public int getCgst() {
		return cgst;
	}

	public int getIgst() {
		return igst;
	}

	public int getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "CreateProduct [name=" + name + ", gsn=" + gsn + ", description=" + description + ", mrp=" + mrp
				+ ", payprice=" + payprice + ", sgst=" + sgst + ", cgst=" + cgst + ", igst=" + igst + ", stock=" + stock
				+ ", categoryLevel1=" + categoryLevel1 + ", categoryLevel2=" + categoryLevel2 + ", categoryLevel3="
				+ categoryLevel3 + "]";
	}	

}
