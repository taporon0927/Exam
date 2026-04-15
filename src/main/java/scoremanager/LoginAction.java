package scoremanager;
// ↑ 成績管理アプリ用のパッケージ

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// ↑ リクエスト・レスポンスを扱うクラス
import tool.Action;
// ↑ 共通の Action クラスを継承する

public class LoginAction extends Action {
    // ↑ Action を継承した「ログイン処理用クラス」

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // ① ローカル変数の宣言
        // 今回は処理がないため、変数の宣言は不要

        // ② リクエストパラメータの取得
        // ログイン画面表示のみなので、パラメータ取得なし

        // ③ DBからデータ取得
        // 認証処理ではないため、DBアクセスは行わない

        // ④ ビジネスロジック
        // 条件分岐や計算処理などは行わない

        // ⑤ DBへデータ保存
        // 登録・更新処理は行わない

        // ⑥ レスポンス値をセット
        // JSPに渡すデータが無いため、セット処理なし

        // ⑦ JSPへフォワード
        // login.jsp に画面遷移する
        req.getRequestDispatcher("login.jsp").forward(req, res);
    }
}