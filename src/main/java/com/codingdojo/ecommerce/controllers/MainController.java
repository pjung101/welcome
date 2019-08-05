package com.codingdojo.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.codingdojo.ecommerce.models.Billing;
import com.codingdojo.ecommerce.models.Category;
import com.codingdojo.ecommerce.models.Order;
import com.codingdojo.ecommerce.models.Product;
import com.codingdojo.ecommerce.models.Review;
import com.codingdojo.ecommerce.models.ShippingAddress;
import com.codingdojo.ecommerce.models.User;
import com.codingdojo.ecommerce.models.Vendor;
import com.codingdojo.ecommerce.repositories.BillingRepo;
import com.codingdojo.ecommerce.repositories.CategoryRepo;
import com.codingdojo.ecommerce.repositories.OrderRepo;
import com.codingdojo.ecommerce.repositories.ProductRepo;
import com.codingdojo.ecommerce.repositories.ReviewRepo;
import com.codingdojo.ecommerce.repositories.ShippingAddressRepo;
import com.codingdojo.ecommerce.repositories.UserRepo;
import com.codingdojo.ecommerce.repositories.VendorRepo;
import com.codingdojo.ecommerce.services.BillingService;
import com.codingdojo.ecommerce.services.CategoryService;
import com.codingdojo.ecommerce.services.OrderService;
import com.codingdojo.ecommerce.services.ProductService;
import com.codingdojo.ecommerce.services.ReviewService;
import com.codingdojo.ecommerce.services.ShippingAddressService;
import com.codingdojo.ecommerce.services.UserService;
import com.codingdojo.ecommerce.services.VendorService;
import com.codingdojo.ecommerce.validator.UserValidator;
import com.codingdojo.ecommerce.validator.VendorValidator;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    private final UserService userService;
    private final UserRepo userRepo;
    private final UserValidator userValidator;

    private final VendorService vendorService;
    private final VendorRepo vendorRepo;
    private final VendorValidator vendorValidator;

    private final ProductService productService;
    private final ProductRepo productRepo;

    private final BillingService billingService;
    private final BillingRepo billingRepo;

    private final CategoryService categoryService;
    private final CategoryRepo categoryRepo;

    private final ReviewService reviewService;
    private final ReviewRepo reviewRepo;

    private final ShippingAddressService shippingAddressService;
    private final ShippingAddressRepo shippingAddressRepo;

    private final OrderService orderService;
    private final OrderRepo orderRepo;

    public MainController(
        UserService userService,
        UserRepo userRepo,
        UserValidator userValidator,
        VendorService vendorService,
        VendorRepo vendorRepo,
        VendorValidator vendorValidator,
        ProductRepo productRepo,
        ProductService productService,
        BillingService billingService,
        BillingRepo billingRepo,
        CategoryService categoryService,
        CategoryRepo categoryRepo,
        ReviewService reviewService,
        ReviewRepo reviewRepo,
        ShippingAddressService shippingAddressService,
        ShippingAddressRepo shippingAddressRepo,
        OrderService orderService,
        OrderRepo orderRepo
    ){
       
        this.userService = userService;
        this.userRepo = userRepo;
        this.userValidator = userValidator;

        this.vendorService = vendorService;
        this.vendorRepo = vendorRepo;
        this.vendorValidator = vendorValidator;

        this.productService = productService;
        this.productRepo = productRepo;

        this.billingService = billingService;
        this.billingRepo = billingRepo;

        this.categoryService = categoryService;
        this.categoryRepo = categoryRepo;

        this.reviewService = reviewService;
        this.reviewRepo = reviewRepo;

        this.shippingAddressService = shippingAddressService;
        this.shippingAddressRepo = shippingAddressRepo;

        this.orderService = orderService;
        this.orderRepo = orderRepo;

    }

    @RequestMapping("/")
    public String dashboard(Model model, HttpSession session) {
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        if(session.getAttribute("userId") != null){
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
        } else if(session.getAttribute("order") != null) {
            
        }else {
            Order order = new Order();
            order.setUserOrder(new ArrayList<Product>());
            session.setAttribute("order", order);
        }
        return "dashboard.jsp";
        
        
    }
    
    @RequestMapping("/guest/guestShippingAddress")
    public String guestShippingAddress(Model model) {
        model.addAttribute("guestShippingAddress", new ShippingAddress());
        return "guestShippingAddress.jsp";
    }

    @PostMapping("/guest/guestShippingAddress")
    public String guestShippingAddressPost(HttpSession session, @Valid @ModelAttribute("guestShippingAddress") ShippingAddress guestShippingAddress, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "guestShippingAddress.jsp";
        } else {
            Order order = (Order) session.getAttribute("order");
            order.setShippingAddress(guestShippingAddress);
            session.setAttribute("order", order);
            return "redirect:/guest/guestBilling";
        }        
    }
    
    @RequestMapping("/guest/guestBilling")
    public String guestBilling(Model model) {
        model.addAttribute("guestBilling", new Billing());
        return "guestBilling.jsp";
    }

    @PostMapping("/guest/guestBilling")
    public String guestBillingPost(HttpSession session, @Valid @ModelAttribute("guestBilling") Billing guestBilling, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "guestBilling.jsp";
        } else {
            Order order = (Order) session.getAttribute("order");
            order.setBilling(new Billing());
            order.setBilling(guestBilling);
            session.setAttribute("order", order);
            return "redirect:/checkout";
        }
    }

    @RequestMapping("/cart")
    public String showCart(Model model, HttpSession session){
        if(session.getAttribute("userId") != null){
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
            List<Product> x = new ArrayList<Product>();
            for(int i = 0; i < user.getCart().size();i++){
                if(x.contains(user.getCart().get(i)) == false){
                    x.add(user.getCart().get(i));
                }
            }
            List<Integer> y = new ArrayList<Integer>();
            int supersum = 0;
            for(int i = 0; i < x.size();i++){
                int counter = 0;
                for(int j = 0; j < user.getCart().size();j++){
                    if(x.get(i) == user.getCart().get(j)){
                        counter++;
                    }
                }
                y.add(counter);
                supersum += counter;
            }
            Double subtotal = 0.00;
            for(int i = 0; i < user.getCart().size(); i++){
                subtotal += user.getCart().get(i).getPrice();
            }
            if(session.getAttribute("vendorId") != null){
                Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
                model.addAttribute("vendor", vendor);
            }
            model.addAttribute("products", x);
            model.addAttribute("counts", y);
            model.addAttribute("subtotal", String.format("%.2f", subtotal));
            model.addAttribute("supersum", supersum);
        } else {
            Order order = (Order) session.getAttribute("order");
            List<Product> x = new ArrayList<Product>();
            for(int i = 0; i < order.getUserOrder().size();i++){
                if(x.contains(order.getUserOrder().get(i)) == false){
                    x.add(order.getUserOrder().get(i));
                }
            }
            List<Integer> y = new ArrayList<Integer>();
            int supersum = 0;
            for(int i = 0; i < x.size();i++){
                int counter = 0;
                for(int j = 0; j < order.getUserOrder().size();j++){
                    if(x.get(i) == order.getUserOrder().get(j)){
                        counter++;
                    }
                }
                y.add(counter);
                supersum += counter;
            }
            Double subtotal = 0.00;
            for(int i = 0; i < order.getUserOrder().size(); i++){
                subtotal += order.getUserOrder().get(i).getPrice();
            }
            if(session.getAttribute("vendorId") != null){
                Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
                model.addAttribute("vendor", vendor);
            }
            model.addAttribute("products", x);
            model.addAttribute("counts", y);
            model.addAttribute("order", order);
            model.addAttribute("subtotal", String.format("%.2f", subtotal));
            model.addAttribute("supersum", supersum);
        }
        return "cart.jsp";
    }
    
    @RequestMapping("/checkout")
    public String checkout(Model model, HttpSession session){
        if(session.getAttribute("userId") != null){
            Order order = new Order();
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("order", order);
            model.addAttribute("user", user);
        } else{
            if(session.getAttribute("order") != null){
                Order order = (Order) session.getAttribute("order");
                User user = new User();
                user.setShippingAddresses(new ArrayList<ShippingAddress>());
                user.setBillings(new ArrayList<Billing>());
                user.getShippingAddresses().add(order.getShippingAddress());
                user.getBillings().add(order.getBilling());
                model.addAttribute("order", order);
                model.addAttribute("user", user);
            } else {
                Order order = new Order();
                User user = new User();
                user.setShippingAddresses(new ArrayList<ShippingAddress>());
                user.setBillings(new ArrayList<Billing>());
                user.getShippingAddresses().add(order.getShippingAddress());
                user.getBillings().add(order.getBilling());
                model.addAttribute("order", order);
                model.addAttribute("user", user);
            }
            
        }
        return "checkout.jsp";
    }
    @RequestMapping("/orderSummary")
    public String orderSummary(Model model, HttpSession session){
        if(session.getAttribute("userId") == null){
            
        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
        }
        return "orderSummary.jsp";
    }
    @PostMapping("/checkout")
    public String confirmCheckout(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model, HttpSession session){
        if(session.getAttribute("userId") != null){
            User user = userService.findUser((Long) session.getAttribute("userId"));
            Product p;
            for(int i = 0; i < user.getCart().size();i++){
                p = productService.findProduct(user.getCart().get(i).getId());
                if(p.getQuantity() != 0){
                    p.setQuantity(p.getQuantity() - 1);
                    productRepo.save(p);
                }
            }
            order.setUserOrder(user.getCart());
            user.setCart(new ArrayList<Product>());
            userRepo.save(user);
            Order newOrder = orderRepo.save(order);
            user.setCart(new ArrayList<Product>());
            userRepo.save(user);
            return "redirect:/orderSummary";
        } else { 
            Order originalOrder = (Order) session.getAttribute("order");
            Product p;
            for(int i = 0; i < originalOrder.getUserOrder().size();i++){
                p = productService.findProduct(originalOrder.getUserOrder().get(i).getId());
                if(p.getQuantity() != 0){
                    p.setQuantity(p.getQuantity() - 1);
                    productRepo.save(p);
                }
            }
            originalOrder.setShippingSpeed(order.getShippingSpeed());
            billingRepo.save(originalOrder.getBilling());
            shippingAddressRepo.save(originalOrder.getShippingAddress());
            Order newOrder = orderRepo.save(originalOrder);
            session.setAttribute("order", null);
            return "redirect:/orderSummary";
        }
    }
    @RequestMapping("/user/{id}/details")
    public String productDetails(Model model, @PathVariable("id") Long id, HttpSession session){
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        if(session.getAttribute("userId") == null){
            Order order = (Order) session.getAttribute("order");
            for(int i = 0; i < order.getUserOrder().size(); i++){
                if(order.getUserOrder().get(i).getId() == id){
                    model.addAttribute("success", "This item is in your cart");
                }
            }
        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            for(int i = 0; i < user.getCart().size();i++){
                if(user.getCart().get(i) == product){
                    model.addAttribute("success", "This item is in your cart");
                }
            }
            model.addAttribute("user", user);
        }

        return "productDetails.jsp";
    }
    @RequestMapping("/user/{id}/delete")
    public String deleteItemOnCart(Model model, @PathVariable("id") Long id, HttpSession session){
        Product product = productService.findProduct(id);
        User user = userService.findUser((Long) session.getAttribute("userId"));
        user.getCart().remove(product);
        userRepo.save(user);
        return "redirect:/cart";
    }
    @RequestMapping("/guest/{id}/delete")
    public String deleteItemOnCartG(Model model, @PathVariable("id") Long id, HttpSession session){
        Order order = (Order) session.getAttribute("order");
        for(int i = order.getUserOrder().size() - 1; i >-1;i--){
            if(order.getUserOrder().get(i).getId() == id){
                order.getUserOrder().remove(i);
            }
        }
        session.setAttribute("order", order);
        return "redirect:/cart";
    }
    @PostMapping("/product/{id}/addToCart")
    public String productToCart(Model model, HttpSession session, @RequestParam("quantity") Integer quantity, @PathVariable("id") Long id){
        Product product = productService.findProduct(id);
        if(session.getAttribute("userId") != null){
            User user = userService.findUser((Long) session.getAttribute("userId"));
            for(int i = 0; i < quantity; i++){
                user.getCart().add(product);
            }
            userRepo.save(user);
            model.addAttribute("product", product);
        } else {
            Order order = (Order) session.getAttribute("order");
            for(int i = 0; i < quantity; i++){
                order.getUserOrder().add(product);
            }
            session.setAttribute("order", order);
            model.addAttribute("product", product);
            model.addAttribute("success", "Successfully added to cart!");
        }
        return "redirect:/user/"+id+"/details";
    }
    @PostMapping("/products/{id}/addReview")
    public String addReview(RedirectAttributes redirectAttributes, HttpSession session, @RequestParam("reviewText") String reviewText, Model model, @PathVariable("id") Long id){
        if(session.getAttribute("userId") != null){
            Product product = productService.findProduct(id);
            User user = userService.findUser((Long) session.getAttribute("userId"));
            Review review = new Review();
            review.setAuthor(user);
            review.setProduct(product);
            review.setText(reviewText);
            Review r = reviewRepo.save(review);
            model.addAttribute("product", product);
        } else {
            redirectAttributes.addFlashAttribute("error", "Please log in to leave a review. Thank you!");
        }
        return "redirect:/user/"+id+"/details";
    }
    @RequestMapping("/signupUser")
    public String signupUser(Model model) {
        model.addAttribute("user", new User());
        return "userIndex.jsp";
    }

    @RequestMapping("/signupVendor")
    public String signupVendor(Model model) {
        model.addAttribute("vendor", new Vendor());
        return "vendorIndex.jsp";
    }
    

    @PostMapping("/createUser")
    public String createUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "userIndex.jsp";
        } else {
            if(userService.findByEmail(user.getEmail()) != null) {
                return "userIndex.jsp";
            }
            User x = userService.registerUser(user);
            session.setAttribute("userId", x.getId());
            return "redirect:/";
        }
    }

    @PostMapping("/createVendor")
    public String createVendor(@Valid @ModelAttribute("vendor") Vendor vendor, BindingResult result, HttpSession session, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            return "vendorIndex.jsp";
        } else {
            if(vendorService.findByEmail(vendor.getEmail()) != null) {
                return "vendorIndex.jsp";
            }
            Vendor x = vendorService.registerVendor(vendor);
            session.setAttribute("vendorId", x.getId());
            return "redirect:/vendor/dashboard";
        }
    }

    @PostMapping("/userLogin")
    public String userLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    Boolean valid = userService.authenticateUser(email, password);
    // if the user is authenticated, save their user id in session
    if(valid) {
        session.setAttribute("userId", userService.findByEmail(email).getId());
        session.setAttribute("vendorId", null);
        User user = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("user", user);
        return "redirect:/";
    } else {
    // else, add error messages and return the login page
        redirectAttributes.addFlashAttribute("error", "Invalid Login");
        return "redirect:/signupUser";
        }
    }

    @RequestMapping("/user/newShippingAddress")
    public String newShippingAddress(Model model) {
        model.addAttribute("newShippingAddress", new ShippingAddress());
        return "newShippingAddress.jsp";
    }

    @PostMapping("/user/newShippingAddress")
    public String newShippingAddressPost(HttpSession session, @Valid @ModelAttribute("newShippingAddress") ShippingAddress newShippingAddress, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "newShippingAddress.jsp";
        } else {
            ShippingAddress s = shippingAddressRepo.save(newShippingAddress);
            User user = userService.findUser((Long) session.getAttribute("userId"));
            user.getShippingAddresses().add(s);
            userRepo.save(user);
            return "redirect:/";
        }
    }
    @RequestMapping("/user/qAddShippingAddress")
    public String newQuasiShippingAddress(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null){

        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
        }
        model.addAttribute("newShippingAddress", new ShippingAddress());
        return "newShippingAddressQ.jsp";
    }

    @PostMapping("/user/qAddShippingAddress")
    public String newQuasiShippingAddressPost(HttpSession session, @Valid @ModelAttribute("newShippingAddress") ShippingAddress newShippingAddress, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "newShippingAddressQ.jsp";
        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            newShippingAddress.setShippingAddressUsers(user);
            ShippingAddress s = shippingAddressRepo.save(newShippingAddress);
            user.getShippingAddresses().add(s);
            userRepo.save(user);
            return "redirect:/checkout";
        }
    }

    @RequestMapping("/user/{id}/editShippingAddress")
    public String editShippingAddress(@PathVariable("id") Long id, Model model) {
        ShippingAddress editShippingAddress = shippingAddressService.findShippingAddress(id);
        ShippingAddress originalShippingAddress = shippingAddressService.findShippingAddress(id);
        model.addAttribute("originalShippingAddress", originalShippingAddress);

        model.addAttribute("editShippingAddress", editShippingAddress);
        return "editShippingAddress.jsp";
    }


    @PostMapping("/user/{id}/editShippingAddress")
    public String editShippingAddressPost(@Valid @ModelAttribute("editShippingAddress") ShippingAddress editShippingAddress, BindingResult result, @PathVariable("id") Long id, Model model) {
        ShippingAddress originalShippingAddress = shippingAddressService.findShippingAddress(id);
        if(result.hasErrors()){
            return "editShippingAddress.jsp";
        } else {
            editShippingAddress.setId(originalShippingAddress.getId());
            shippingAddressRepo.save(editShippingAddress);                
            return "redirect:/";
        }
    }

    @RequestMapping("/user/newBilling")
    public String newBilling(Model model) {
        model.addAttribute("newBilling", new Billing());
        return "newBilling.jsp";
    }

    @PostMapping("/user/newBilling")
    public String newBillingPost(@Valid @ModelAttribute("newBilling") Billing newBilling, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "newBilling.jsp";
        } else {
            billingRepo.save(newBilling);
            return "userDashboard.jsp";
        }
    }
    @RequestMapping("/user/quasiAddBilling")
    public String newBillingQ(Model model, HttpSession session) {
        if(session.getAttribute("userId") == null){

        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            model.addAttribute("user", user);
        }
        model.addAttribute("newBilling", new Billing());
        return "newBillingQ.jsp";
    }

    @PostMapping("/user/quasiAddBilling")
    public String newBillingPostQ(HttpSession session, @Valid @ModelAttribute("newBilling") Billing newBilling, BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "newBillingQ.jsp";
        } else {
            User user = userService.findUser((Long) session.getAttribute("userId"));
            Billing b = billingRepo.save(newBilling);
            user.getBillings().add(b);
            userRepo.save(user);
            return "redirect:/checkout";
        }
    }        

    @RequestMapping("/user/{id}/editBilling")
    public String editBilling(@PathVariable("id") Long id, Model model) {
        Billing editBilling = billingService.findBilling(id);
        model.addAttribute("editBilling", editBilling);
        return "editBilling.jsp";
    }

    @PostMapping("/user/{id}/editBilling")
    public String editBillingPost(@Valid @ModelAttribute("editBilling") Billing editBilling, BindingResult result, @PathVariable("id") Long id, Model model) {
        Billing originalBilling = billingService.findBilling(id);
        if(result.hasErrors()){
            return "editBilling.jsp";
        } else {
            editBilling.setId(originalBilling.getId());
            billingRepo.save(editBilling);                
            return "userDashboard.jsp";
        }
    }

    @RequestMapping("/product/{id}")
    public String productDetails(@PathVariable("id") Long id,Model model, HttpSession session){
        Product product = productService.findProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("user", new User());
        return "productDetails.jsp";
    }


    @PostMapping("/vendorLogin")
    public String vendorLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    Boolean valid = vendorService.authenticateVendor(email, password);
    // if the user is authenticated, save their user id in session
    if(valid) {
        session.setAttribute("vendorId", vendorService.findByEmail(email).getId());
        session.setAttribute("userId", null);
        return "redirect:/vendor/dashboard";
    } else {
    // else, add error messages and return the login page
        redirectAttributes.addFlashAttribute("error", "Invalid Login");
        return "redirect:/signupVendor";
        }
    }
    @RequestMapping("/vendor/dashboard")
    public String vendorDashboard(Model model, HttpSession session){
        Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
        model.addAttribute("vendor", vendor);
        return "vendorDashboard.jsp";
    }
    @RequestMapping("/vendor/{id}/delete")
    public String vendorDeleteProduct(@PathVariable("id") Long id, Model model, HttpSession session){
        productRepo.deleteById(id);
        return "redirect:/vendor/dashboard";
    }
    @RequestMapping("/vendor/addProduct")
    public String vendorAddProductPage(Model model, HttpSession session){
        Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("vendor", vendor);
        model.addAttribute("product", new Product());
        return "vendorAddProduct.jsp";
    }
    @PostMapping("/vendor/addProduct")
    public String vendorAddProductPage(Model model,@Valid @ModelAttribute("product") Product product, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            List<Category> categories = categoryRepo.findAll();
            model.addAttribute("categories", categories);
            return "vendorAddProduct.jsp";
        } else {
            Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
            product.setVendor(vendor);
            productRepo.save(product);
            return "redirect:/vendor/dashboard";
        }
    }
    @RequestMapping("/vendor/{id}/edit")
    public String vendorEditProductPage(@PathVariable("id") Long id,Model model, HttpSession session){
        Product product = productService.findProduct(id);
        Product originalProduct = productService.findProduct(id);
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("originalProduct", originalProduct);
        return "vendorEditProduct.jsp";
    }
    @PostMapping("/vendor/{id}/edit")
    public String vendorEditProduct(Model model,@PathVariable("id") Long id,@Valid @ModelAttribute("product") Product product, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            Product originalProduct = productService.findProduct(id);
            List<Category> categories = categoryRepo.findAll();
            model.addAttribute("categories", categories);
            model.addAttribute("originalProduct", originalProduct);
            return "vendorEditProduct.jsp";
        } else {
            Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
            product.setVendor(vendor);
            productRepo.save(product);
            return "redirect:/vendor/dashboard";
        }
    }

    @RequestMapping("/vendor/banking")
    public String bankingInfo(){
        return "vendorBanking.jsp";
    }

    @RequestMapping("/vendor/analytics")
    public String analytics(Model model, HttpSession session){
        Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
        List<Category> categories  = categoryRepo.findAll();
        ArrayList<Integer> numList = new ArrayList<Integer>();
        for(int i = 0; i < categories.size(); i++){
            int counter = 0;
            for(int j = 0; j < vendor.getProducts().size(); j++){
                if(vendor.getProducts().get(j).getCategory().getName().equals(categories.get(i).getName()) == true){
                    counter++;
                }
            }
            numList.add(counter);
        }
        model.addAttribute("vendor",vendor);
        model.addAttribute("numList", numList);
        model.addAttribute("categories", categories);

        return "vendorAnalytics.jsp";
    }

    @RequestMapping("/vendor/editVendorAccount")
    public String editVendorAccount(Model model, HttpSession session){
        Vendor vendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
        Vendor originalVendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
        model.addAttribute("originalVendor", originalVendor);
        model.addAttribute("vendor", vendor);
        return "editVendorAccount.jsp";
    }
    @PostMapping("/vendor/editVendorAccount")
    public String vendorEdit(Model model, @Valid @ModelAttribute("vendor") Vendor vendor, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            Vendor originalVendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
            model.addAttribute("originalVendor", originalVendor);
            return "editVendorAccount.jsp";
        } else {
            Vendor originalVendor = vendorService.findVendor((Long) session.getAttribute("vendorId"));
            vendor.setId(originalVendor.getId());
            vendorRepo.save(vendor);
            return "redirect:/vendor/dashboard";
        }
    }
    @RequestMapping("/adminLogin")
    public String adminLogin(){
        return "adminIndex.jsp";
    }
    @PostMapping("/adminLogin")
    public String adminLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
    Boolean valid = userService.authenticateUser(email, password);
    // if the user is authenticated, save their user id in session
    if(valid) {
        User user = userService.findByEmail(email);
        if(user.getAdminLevel() == 1){
            session.setAttribute("userId", userService.findByEmail(email).getId());
            return "redirect:/admin/dashboard";
        }
        return "redirect:/";
        
    } else {
    // else, add error messages and return the login page
        redirectAttributes.addFlashAttribute("error", "Invalid Login");
        return "redirect:/";
        }
    }
    @RequestMapping("/admin/dashboard")
    public String adminDashboard(Model model, HttpSession session){
        User admin = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("admin", admin);
        model.addAttribute("searchUser", new User());
        model.addAttribute("searchVendor", new Vendor());
        return "adminDashboard.jsp";
    }
    @PostMapping("/admin/dashboard/searchUser")
    public String adminDashboardSearchUser(RedirectAttributes redirectAttributes, @ModelAttribute("searchUser") User searchUser, Model model, HttpSession session){
        User admin = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("admin", admin);
        model.addAttribute("searchUser", new User());
        model.addAttribute("searchVendor", new Vendor());
        if(userService.findByEmail(searchUser.getEmail()) != null){
            model.addAttribute("user", userService.findByEmail(searchUser.getEmail()));
            return "adminDashboard.jsp";
        } else {
            redirectAttributes.addFlashAttribute("userError", "User does not exist");
            return "redirect:/admin/dashboard";
        }
    }
    @PostMapping("/admin/dashboard/searchVendor")
    public String adminDashboardSearchVendor(RedirectAttributes redirectAttributes, @ModelAttribute("searchVendor") User searchVendor, Model model, HttpSession session){
        User admin = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("admin", admin);
        model.addAttribute("searchUser", new User());
        model.addAttribute("searchVendor", new Vendor());
        if(vendorService.findByEmail(searchVendor.getEmail()) != null){
            model.addAttribute("vendor", vendorService.findByEmail(searchVendor.getEmail()));
            return "adminDashboard.jsp";
        } else {
            redirectAttributes.addFlashAttribute("userVendor", "Vendor does not exist");
            return "redirect:/admin/dashboard";
        }
    }
    @RequestMapping("/admin/addCategory")
    public String adminAddCategoryPage(Model model, HttpSession session){
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("category", new Category());
        model.addAttribute("categories", categories);
        return "adminAddCategory.jsp";
    }
    @PostMapping("/admin/addCategory")
    public String adminAddCategory(Model model, @Valid @ModelAttribute("category") Category category, BindingResult result, HttpSession session){
        if(result.hasErrors()){
            List<Category> categories = categoryRepo.findAll();
            model.addAttribute("categories", categories);
            return "adminAddCategory.jsp";
        }
        categoryRepo.save(category);
        return "redirect:/admin/addCategory";
    }
    @RequestMapping("/admin/{id}/delete")
    public String adminDeleteCategory(@PathVariable("id") Long id){
        categoryRepo.deleteById(id);
        return "redirect:/admin/addCategory";
    }
    @RequestMapping("/admin/{id}/edit")
    public String adminEditCategoryPage(Model model, @PathVariable("id") Long id){
        Category category = categoryService.findCategory(id);
        Category originalCategory = categoryService.findCategory(id);
        model.addAttribute("originalCategory", originalCategory);
        model.addAttribute("category", category);
        return "adminEditCategory.jsp";
    }
    @PostMapping("/admin/{id}/edit")
    public String adminEditCategory(RedirectAttributes redirectAttributes,@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, @PathVariable("id") Long id){
        if(result.hasErrors()){
            Category originalCategory = categoryService.findCategory(id);
            model.addAttribute("originalCategory", originalCategory);
            return "adminEditCategory.jsp";
        } else{
            if(categoryService.findCategoryByName(category.getName()) == null){
                Category originalCategory = categoryService.findCategory(id);
                originalCategory.setName(category.getName());
                categoryRepo.save(originalCategory);
                return "redirect:/admin/addCategory";
            } else {
                redirectAttributes.addFlashAttribute("error", "Category already exists in the database");
                return "redirect:/admin/"+id+"/edit";
            }
        }
    }
    @RequestMapping("/admin/{id}/editUser")
    public String adminEditUserPage(Model model, @PathVariable("id") Long id){
        User user = userService.findUser(id);
        User originalUser = userService.findUser(id);
        model.addAttribute("originalUser", originalUser);
        model.addAttribute("user", user);
        return "adminEditUser.jsp";
    }
    @PostMapping("/admin/{id}/editUser")
    public String adminEditUser(@Valid @ModelAttribute("user") User user, BindingResult result, @RequestParam("password") String password, Model model, @PathVariable("id") Long id){
        User originalUser = userService.findUser(id);

        if(result.hasErrors()){
            return "adminEditUser.jsp";
        } else {
            if(user.getEmail().equals(originalUser.getEmail()) == false || userService.findByEmail(user.getEmail()) == null){
                user.setId(originalUser.getId());
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                user.setPassword(hashed);
                userRepo.save(user);
            }
            return "redirect:/admin/dashboard";
        }
        
    }
    @RequestMapping("/admin/{id}/deleteUser")
    public String adminDeleteUser(Model model, @PathVariable("id") Long id){
        userRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
    @RequestMapping("/admin/{id}/deleteVendor")
    public String adminDeleteVendor(Model model, @PathVariable("id") Long id){
        vendorRepo.deleteById(id);
        return "redirect:/admin/dashboard";
    }
    @RequestMapping("/admin/{id}/editVendor")
    public String adminEditVendorPage(Model model, @PathVariable("id") Long id){
        Vendor vendor = vendorService.findVendor(id);
        Vendor originalVendor = vendorService.findVendor(id);
        model.addAttribute("originalVendor", originalVendor);
        model.addAttribute("vendor", vendor);
        return "adminEditVendor.jsp";
    }
    @PostMapping("/admin/{id}/editVendor")
    public String adminEditVendor(@Valid @ModelAttribute("vendor") Vendor vendor, BindingResult result, @RequestParam("password") String password, Model model, @PathVariable("id") Long id){
        Vendor originalVendor = vendorService.findVendor(id);
        if(result.hasErrors()){
            return "adminEditVendor.jsp";
        } else {
            if(vendor.getEmail().equals(originalVendor.getEmail()) == false || vendorService.findByEmail(vendor.getEmail()) == null){
                vendor.setId(originalVendor.getId());
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                vendor.setPassword(hashed);
                vendorRepo.save(vendor);
            }
            return "redirect:/admin/" + id + "/editVendor";
        }
    }

    @RequestMapping("/editUser")
    public String userEdit(Model model, HttpSession session){
        User user = userService.findUser((Long) session.getAttribute("userId"));
        User originalUser = userService.findUser((Long) session.getAttribute("userId"));
        model.addAttribute("originalUser", originalUser);
        model.addAttribute("user", user);
        return "editUser.jsp";
    }
    @PostMapping("/edit/User")
    public String editUserPage(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        if(result.hasErrors()) {
            return "editUser.jsp";
        } else {
            User originalUser = userService.findUser((Long) session.getAttribute("userId"));
            originalUser.setFirstName(user.getFirstName());
            originalUser.setLastName(user.getLastName());
            originalUser.setEmail(user.getEmail());
            userRepo.save(originalUser);
            return "redirect:/";                                                                                                                                                                    
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    
}
