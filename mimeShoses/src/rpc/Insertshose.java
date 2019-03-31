package rpc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import db.DBConnection;
import db.DBConnectionFactory;

/**
 * Servlet implementation class Insertshose
 */
@WebServlet("/insert")
public class Insertshose extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertshose() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DBConnection connection = DBConnectionFactory.getConnection();
		try {
			JSONObject input = RpcHelper.readJSONObject(request);
			String shosesid = input.getString("shose_id");
			String name = input.getString("name");
			String category = input.getString("category");
			String color = input.getString("color");
			String vendor = input.getString("vendor");
			String description = input.getString("description");	
			String imageUrl = input.getString("image_url");	
			String price = input.getString("price");
			String size = input.getString("size");
			String width = input.getString("width");
			String type = input.getString("type");
			JSONObject obj = new JSONObject();
			if (connection.insertShoses(shosesid, name, category, color, vendor, description, imageUrl, price, size, width, type)) {
				obj.put("status", "OK");
			} else {
				obj.put("status", "error in the server");
			}
			RpcHelper.writeJsonObject(response, obj);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			connection.close();
		}
	}
}

