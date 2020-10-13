package org.fusesource.restygwt.server.basic;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fusesource.restygwt.client.basic.JsonPatchOperationDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

public class PatchTestGwtServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (!method.equals("PATCH")) {
            Object[] errArgs = new Object[]{method};
            String errMsg = MessageFormat.format("HTTP method {0} is not supported by this URL", errArgs);
            resp.sendError(501, errMsg);
        }

        List<JsonPatchOperationDTO> operations = new ObjectMapper()
                .readValue(req.getInputStream(), new TypeReference<List<JsonPatchOperationDTO>>() {});
        assert !operations.isEmpty();
        assert "replace".equals(operations.get(0).getOp());
        assert "/name".equals(operations.get(0).getPath());

        resp.setStatus(200);
        resp.setContentType("application/json");
        resp.getWriter().print("{\"name\":\"" + operations.get(0).getValue() + "\"}");

    }
}
