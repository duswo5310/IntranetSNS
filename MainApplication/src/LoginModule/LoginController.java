package LoginModule;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CreateDialogModule.ChkDialogMain;
import Dao.LoginDao;
import IdSaveLoadModule.IdSaveLoad;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
/*
������Ʈ ���� : �系 SNS
���α׷� ���� : 0.7.0
��� �̸� : �α���
��� ���� : 1.1.2
Ŭ���� �̸� : LoginController
�ش� Ŭ���� �ۼ� : �ֹ���, �赵��

�ʿ� ��� Java����
- LoginMain.java (�α��� ȭ���� ����Ǵ� ���� Ŭ����)
- LoginController.java (�α��� â ��Ʈ�ѷ�)
- SignUpController.java (����� ��� â ��Ʈ�ѷ�)
- FindAccountController.java (���� ã�� â ��Ʈ�ѷ�)
- User.java (����� ��Ͽ� ����ϴ� ����� ���� Ŭ����[������� ��� ������ ��� ����])
- UserData.java (���� ã�⿡�� ����ϴ� ����� ���� Ŭ����[����ڹ�ȣ, �̸�, �̸���, ��й�ȣ])

�ʿ� fxml����
- login.fxml (�α��� â fxml)
- signUp.fxml (����ڵ�� â fxml)
- findAccount.fxml (���� ã�� â fxml)

�ʿ� import ����� ���� package
- Dao.LoginDao (�α��� ������ ������ ���̽��� ó���� �� �ִ� �޼���)
- EncryptionDecryption.PasswordEncryption (��й�ȣ�� ��ȣȭ�ϰ� ��ȣȭ�ϴ� �޼��带 �����ϰ� ����)
- ChkDialogModule.ChkDialogMain (�ȳ��� ����� ���� �ӽ� ���̾�α׸� �����ϴ� ��Ű��)
- SendMail.SendMail (���� ������ �޼��带 �����ϰ� ����)

�ش� Ŭ���� �ֿ� ���
- �α��� ����ȭ�� ��Ʈ�ѷ�
- ����ڰ� ����ڹ�ȣ�� ��й�ȣ�� �Է��ϰ� �α����� �õ��ϸ� �����ͺ��̽����� ��ġ�ϴ��� Ȯ���� ��
- ��ġ�Ѵٸ� ����ȭ������ ��ȯ�ϰ� �׷��� �ʴٸ� ������ ����ش�.
- ����� ��� �Ǵ� ���� ã�� ��ư�� ���� ��� �ش��ϴ� �������� ��ȯ

��� ���� ���� ����
1.1.0
- DAO �ν��Ͻ��� �ʿ�ÿ��� ������ ������ �̵� ���� �ε� �ð��� ����.
- ����� ���â���� �̸��� �ߺ�üũ ��ư �߰� �� �̸��� �ߺ�üũ �׼� �߰�
- LoginDao Ŭ������ �̸��� üũ�ϴ� �޼��� �߰�
- ���� �� �޼��� �̸� ����ȭ

1.1.1
- Dao �ν��Ͻ� ���� (������ ���̽� �ʱ�ȭ Ŭ���� ����)
- �޺��ڽ��� ������ �����ͺ��̽��� ����

1.1.2
- ��ȭ��ȣ �ߺ�üũ ��ư, ��� �߰�
 */
public class LoginController implements Initializable {	
	@FXML private Button btnLogin, btnFindAccount, btnSignUp;
	@FXML private TextField fieldUserNo;
	@FXML private PasswordField fieldPassword;
	
	LoginDao loginDao = new LoginDao();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnLogin.setOnAction(event -> handleBtnLoginAction());
		btnFindAccount.setOnAction(event -> handleBtnFindAccountAction());
		btnSignUp.setOnAction(event -> handleBtnSignUpAction());
		
		//��й�ȣ�� �Է��ϰ� �α��� ��ư�� �������ʰ� ����Ű�� ������ �α����� �õ��� �� �ְ� ����.
		fieldPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				KeyCode keyCode = event.getCode();
				if(keyCode.equals(KeyCode.ENTER)) {
					handleBtnLoginAction();
				}
			}
		});
	}
	
	//�α��� ��ư�� ��������
	//1. �����ȣ�� �Է����� �ʾҴٸ� �����ȣ�� �Է��϶�� ���̾�α� ���
	//2. ��й�ȣ�� �Է����� �ʾҴٸ� ��й�ȣ�� �Է��϶�� ���̾�α� ���
	//3_1. ���� �����ȣ�� ��й�ȣ �� �� �ϳ��� Ʋ�ȴٸ�(���ϰ����� null�� ����) ������ Ȯ���϶�� ���̾�α� ���
	//3_2. �����ͺ��̽����� �α��� ���°� 1�̶�� ��򰡿��� �α����� �Ǿ��ִٴ� �ǹ��̴�
	//�α��ο� �����ߴٴ� ���̾�α� ���
	//3_3. �α��ο� �����ߴٸ�(���ϰ����� �̸��� ����) ����� �̸��� ���鼭 �α��ο� �����ߴٴ� ���̾�α� ���
	//3_4. �α��ο� �����ϸ� �����ͺ��̽����� �ش� ����� �α��� ���¸� 0���� 1�� ����
	public void handleBtnLoginAction() {
		String labelText = new String();
		if(fieldUserNo.getText().isEmpty() || fieldPassword.getText().isEmpty()) {	//�����ȣ, ��й�ȣ �� �� �ϳ��� �Է����� �ʾҴٸ�
			if(fieldUserNo.getText().isEmpty()) {
				labelText = "�����ȣ�� �Է��ϼ���.";
				fieldUserNo.requestFocus();
			}
			else {
				labelText = "��й�ȣ�� �Է��ϼ���.";
				fieldPassword.requestFocus();
			}
			ChkDialogMain.chkDialog(labelText);
		}
		else {	//���� �Է��ߴٸ� �ϴ� �Ѿ
			String name = loginDao.chkUserData(fieldUserNo.getText(), fieldPassword.getText());
			if(name == null) {	//�Է��� ������ ���� �ʴٴ� ��
				labelText = "��ġ�ϴ� ȸ�������� �����ϴ�.";
				ChkDialogMain.chkDialog(labelText);
			}
			else {	//�װ� �ƴ϶�� �Է��� ������ �ùٸ�
//				labelText = name + "��\n�������!";
//				ChkDialogMain.chkDialog(labelText);
				Stage stage = (Stage)btnLogin.getScene().getWindow();
				try {
					Parent mainPane = FXMLLoader.load(getClass().getResource("/MainModule/main.fxml"));
					Scene scene = new Scene(mainPane);
					stage.setScene(scene);
					stage.setResizable(false);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//����ڹ�ȣ�� �ؽ�Ʈ���Ϸ� ����
				IdSaveLoad.saveUserId(fieldUserNo.getText());
			}
		}
	}
	
	//����ã�� ��ư�� ������ ��
	//����ã�� â���� ��ȯ��
	public void handleBtnFindAccountAction() {
		Stage stage = (Stage)btnFindAccount.getScene().getWindow();
		try {
			Parent findAccountDialog = FXMLLoader.load(getClass().getResource("findAccount.fxml"));
			Scene scene = new Scene(findAccountDialog);
			stage.setTitle("���� ã��");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//����ڵ�� ��ư�� ������ ��
	//����ڵ�� â���� ��ȯ��
	public void handleBtnSignUpAction() {
		Stage stage = (Stage)btnSignUp.getScene().getWindow();
		try {
			Parent signUpDialog = FXMLLoader.load(getClass().getResource("signUp.fxml"));
			Scene scene = new Scene(signUpDialog);
			stage.setTitle("����� ���");
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}