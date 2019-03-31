package entity;

import org.json.JSONArray;
import org.json.JSONObject;

public class shoses {
	private String shoseId;
	private String category;
	private String name;
	private String type;
	private String color;
	private String vendor;
	private String imageUrl;
	private String description;
	private String price;
	private String size;
	private String width;
	
	private shoses(ShosesBuilder builder) {
		this.shoseId = builder.shoseId;
		this.category = builder.category;
		this.type = builder.type;
		this.color = builder.color;
		this.vendor = builder.vendor;
		this.imageUrl = builder.imageUrl;
		this.description = builder.description;
		this.price = builder.price;
		this.size = builder.size;
		this.width = builder.width;
		this.name = builder.name;
	}
	
	public String getShoseId() {
		return shoseId;
	}
	
	public String getCategory() {
		return category;
	}
		
	public String getType() {
		return type;
	}
	
	public String getColor() {
		return color;
	}
		
	public String getVendor() {
		return vendor;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
		
	public String getDescription() {
		return description;
	}
	
	public String getPrice() {
		return price;
	}
	
	
	public String getSize() {
		return size;
	}
	
	public String getWidth() {
		return width;
	}
	
	public JSONObject toJSONObject() {
		  JSONObject object = new JSONObject();
		  try {
			  object.put("shoseId", shoseId);
			  object.put("name", name);
			  object.put("category", category);
			  object.put("color", color);
			  object.put("vendor", vendor);
			  object.put("image_url", imageUrl);
			  object.put("description", description);
			  object.put("price", price);
			  object.put("size", size);
			  object.put("width", width);
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		  
		  return object;
	  }
	
	public static class ShosesBuilder {
		private String shoseId;
		private String category;
		private String type;
		private String color;
		private String vendor;
		private String imageUrl;
		private String description;
		private String name;
		private String price;
		private String size;
		private String width;
		
		public ShosesBuilder setShoseId(String shoseId) {
			this.shoseId = shoseId;
			return this;
		}
		
		public ShosesBuilder setCategory(String category) {
			this.category = category;
			return this;
		}
		
		public ShosesBuilder setName(String name) {
			this.name = category;
			return this;
		}
		
		public ShosesBuilder setType(String type) {
			this.type = type;
			return this;
		}
		
		public ShosesBuilder setColor(String color) {
			this.color = color;
			return this;
		}
	
		public ShosesBuilder setVendor(String vendor) {
			this.vendor = vendor;
			return this;
		}
		
		public ShosesBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}
		
		public ShosesBuilder setDescription(String description) {
			this.description = description;
			return this;
		}
		
		public ShosesBuilder setPrice(String price) {
			this.price = price;
			return this;
		}
		
		public ShosesBuilder setSize(String size) {
			this.size = size;
			return this;
		}
		
		public ShosesBuilder setWidth(String width) {
			this.width = width;
			return this;
		}
		
		public shoses build() {
			return new shoses(this);
		}
	}
}
