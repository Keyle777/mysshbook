package top.keyle.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.keyle.pojo.CartItem;
import top.keyle.pojo.User;
import top.keyle.service.CartItemService;
import top.keyle.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/user")
@Controller
@SuppressWarnings("all")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    CartItemService cartItemService;
    MessageDigest md5;


    @RequestMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 使用gif验证码
        GifCaptcha gifCaptcha = new GifCaptcha(110,48,4);
        gifCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        CaptchaUtil.out(gifCaptcha, request, response);
    }
    @RequestMapping("/regist")
    public String regist(String uname, String pwd, String email, Integer role, String captcha, HttpServletRequest request) throws NoSuchAlgorithmException {
        if (!CaptchaUtil.ver(captcha, request)) {
            CaptchaUtil.clear(request);  // 清除session中的验证码
            request.getSession().setAttribute("msg","验证码错误！");
            return "user/regist";
        }
        String uri = request.getRequestURI();
        if(uname !=null && uname !="" && pwd !=null && pwd != ""){
            User user = new User(uname,pwd,email,role,null,new CartItem());
            pwd = user.getPwd();
            md5 = MessageDigest.getInstance("MD5");
            md5.update(pwd.getBytes());
            pwd = new BigInteger(1, md5.digest()).toString(16);
            user.setPwd(pwd);
            Integer row = userService.insertUser(user);
            if (row > 0){
                request.getSession().setAttribute("currentUser",userService.detailUser(user.getId()));
                return "user/regist_success";
            }
        }
        request.getSession().setAttribute("msg","未输入账号或密码！");
            return "user/regist";
    }

    @RequestMapping("/login")
    public String login(String uname , String pwd, HttpSession session) throws NoSuchAlgorithmException {
        md5 = MessageDigest.getInstance("MD5");
        md5.update(pwd.getBytes());
        pwd = new BigInteger(1, md5.digest()).toString(16);
        User user = userService.selectUser(uname, pwd);
        if(user !=null ){
            //得到这个人的完整信息并保存！
            session.setAttribute("currentUser",userService.detailUser(user.getId()));
            List<CartItem> cartItemList = cartItemService.selectCartItem(user.getId());
            session.setAttribute("cartItemList",cartItemList);
            session.setAttribute("sumCartItemList",cartItemList.size());
            Integer[] batchCartItemIds = new Integer[cartItemList.size()];
            int k = 0;
            Double everySumMoney = 0.0;
            for (CartItem item : cartItemList) {
                everySumMoney += item.getSumMoney();
                batchCartItemIds[k]=item.getId();
            }
            session.setAttribute("everySumMoney",everySumMoney);
            return "user/login_success";
        }else {
            session.setAttribute("logmsg","请输入正确的账号或密码！");
            return "user/login";
        }
    }

    @RequestMapping("/exit")
    public String exit(HttpSession session){
        session.removeAttribute("currentUser");
        return "redirect:/book/index";
    }
}
