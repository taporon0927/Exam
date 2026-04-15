package scoremanager;

import java.util.ArrayList;
import java.util.List;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // ① ローカル変数の宣言
        String url = "";              // 遷移先URL
        String id = "";               // 教員ID
        String password = "";         // パスワード
        TeacherDao teacherDao = new TeacherDao(); // 教員DAO
        Teacher teacher = null;       // 教員情報

        // ② リクエストパラメータの取得（フォーム入力値）
        id = req.getParameter("id");          // 教員IDを取得
        password = req.getParameter("password"); // パスワードを取得

        // ③ DBからデータ取得（ログイン認証）
        teacher = teacherDao.login(id, password);

        // ④ ビジネスロジック
        // ⑦ フォワード／リダイレクト
        // 認証結果によって処理を分岐
        if (teacher != null) {
            // ===== ログイン成功 =====

            // セッションを取得（なければ新規作成）
            HttpSession session = req.getSession(true);

            // 認証済みフラグを立てる
            teacher.setAuthenticated(true);

            // ログイン情報をセッションに保存
            session.setAttribute("user", teacher);

            // メニュー画面へリダイレクト
            url = "main/Menu.action";
            res.sendRedirect(url);

        } else {
            // ===== ログイン失敗 =====

            // エラーメッセージを格納するリスト
            List<String> errors = new ArrayList<>();
            errors.add("IDまたはパスワードが確認できませんでした");

            // リクエストにエラーメッセージを保存
            req.setAttribute("errors", errors);

            // 入力されたIDをそのまま戻す
            req.setAttribute("id", id);

            // ログイン画面へフォワード
            url = "login.jsp";
            req.getRequestDispatcher(url).forward(req, res);
        }
    }
}