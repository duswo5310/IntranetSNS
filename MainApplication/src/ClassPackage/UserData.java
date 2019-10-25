package ClassPackage;
/*
������Ʈ ���� : �系 SNS
���α׷� ���� : 0.7.0
��� �̸� : Ŭ���� ��Ű��
��� ���� : 1.1.2
Ŭ���� �̸� : UserData
�ش� Ŭ���� �ۼ� : �ֹ���

�ʿ��� Java����
- UserData.java (���� ã�⿡�� ����ϴ� ����� ���� Ŭ����[����ڹ�ȣ, �̸�, �̸���, ��й�ȣ])

�ʿ� import ����� ���� package
- Dao.LoginDao (�α��� ������ ������ ���̽��� ó���� �� �ִ� �޼���)
- EncryptionDecryption.PasswordEncryption (��й�ȣ�� ��ȣȭ�ϰ� ��ȣȭ�ϴ� �޼��带 �����ϰ� ����)
- ChkDialogModule.ChkDialogMain (�ȳ��� ����� ���� �ӽ� ���̾�α׸� �����ϴ� ��Ű��)
- SendMail.SendMail (���� ������ �޼��带 �����ϰ� ����)

�ش� Ŭ���� �ֿ� ���
- 

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
- ����� ����, ���� �޽�����, ������Ȯ�� ���� �߰�
 */
public class UserData {
	private String userNo;
	private String userName;
	private String userPw;
	private String userMail;
	
	public UserData(String userNo, String userName, String userPw, String userMail) {
		this.userNo = userNo;
		this.userName = userName;
		this.userPw = userPw;
		this.userMail = userMail;
	}
	
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
}