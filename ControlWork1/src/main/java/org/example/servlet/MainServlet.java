package org.example.servlet;

import org.example.service.AccountServiceImpl;
import org.example.service.ExhibitionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/")
public class MainServlet extends HttpServlet {


    private final AccountServiceImpl accountService = new AccountServiceImpl();

    private final ExhibitionService exhibitionService = ExhibitionService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("logout") != null) {
            req.getSession().invalidate();
            resp.sendRedirect("/");
        } else {
//            if (req.getParameter("deleteManga") != null) {
//                exhibitionService.delete(Long.valueOf(req.getParameter("deleteManga")));
//            } else {
//                accountService.createBooking(req);
//            }
            accountService.createBooking(req);
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("mangaList", exhibitionService.getAll());
        req.getRequestDispatcher("/main.ftl").forward(req, resp);
    }
}

