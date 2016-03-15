package controler;

import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.dao.DBProductDAO;
import model.Case;
import model.Cpu;
import model.Gpu;
import model.HardDrive;
import model.Monitor;
import model.Motherboard;
import model.Ram;

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Need check method because of parsing!!!
		
		String producer = request.getParameter("producer");
		String model = request.getParameter("model");
		if (isUniqueModel(model)) {
				String type = request.getParameter("type");
				double price = Double.parseDouble(request.getParameter("price"));
				String info = request.getParameter("info");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				String imgUrl = request.getParameter("imgUrl");
		
				DBProductDAO prdao = DBProductDAO.getDBProductDAO();
		
		
		switch (type) {
			case "case":
				String form = request.getParameter("form");
				String size = request.getParameter("size");
				Case cs = new Case(producer, model, price, info, quantity, imgUrl, form, size);
				prdao.addProduct(cs);
			break;
			
			case "cpu":
				int numberOfCores = Integer.parseInt(request.getParameter("number_of_cores"));
				double clockSpeed = Double.parseDouble(request.getParameter("clock_speed"));
				String socket = request.getParameter("socket");
				Cpu cpu = new Cpu(producer, model, price, info, quantity, imgUrl, numberOfCores, clockSpeed, socket);
				prdao.addProduct(cpu);
			break;
			
			case "gpu":
				int memorySize = Integer.parseInt(request.getParameter("memory_size"));
				String maxResolution = request.getParameter("max_resolution");
				String outputInterface = request.getParameter("output_interface");
				Gpu gpu = new Gpu(producer, model, price, info, quantity, imgUrl, memorySize, maxResolution, outputInterface);
				prdao.addProduct(gpu);
			break;
			
			case "hd":
				String hdType = request.getParameter("hd_type");
				double hdSize = Double.parseDouble(request.getParameter("size"));
				int capacity = Integer.parseInt(request.getParameter("capacity"));
				HardDrive hd = new HardDrive(producer, model, price, info, quantity, imgUrl, hdType, hdSize, capacity);
				prdao.addProduct(hd);
			break;
			
			case "monitor":
				double monSize = Double.parseDouble(request.getParameter("size"));
				int refreshRate = Integer.parseInt(request.getParameter("refresh_rate"));
				String matrixType = request.getParameter("matrix_type");
				Monitor mon = new Monitor(producer, model, price, info, quantity, imgUrl, monSize, refreshRate, matrixType);
				prdao.addProduct(mon);
			break;
			
			case "mb":
				String chipset = request.getParameter("chipset");
				int ramSlots = Integer.parseInt(request.getParameter("ram_slots"));
				String mbSocket = request.getParameter("socket");
				Motherboard mb = new Motherboard(producer, model, price, info, quantity, imgUrl, chipset, ramSlots, mbSocket);
				prdao.addProduct(mb);
			break;
			
			case "ram":
				String ramType = request.getParameter("ram_type");
				int ramSize = Integer.parseInt(request.getParameter("size"));
				Ram ram = new Ram(producer, model, price, info, quantity, imgUrl, ramType, ramSize);
				prdao.addProduct(ram);
			break;
		}
			
		request.setAttribute("has_such_product", false);
		request.getRequestDispatcher("add_case.jsp").forward(request, response);
		
			
			
		} else {
			request.setAttribute("has_such_product", true);
			request.getRequestDispatcher("add_case.jsp").forward(request, response);
		}
		
		
	

		
		
		
	}
	
	private boolean isUniqueModel(String model) {
		DBProductDAO prdao = DBProductDAO.getDBProductDAO();
		HashSet<String> allModels = prdao.getAllModels();
		
		if (allModels.contains(model)) {
			return false;
		}
		
		return true;
	}
	

}
