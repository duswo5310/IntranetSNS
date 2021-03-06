package ClassPackage;
/*
프로젝트 주제 : 사내 SNS
프로그램 버전 : 1.0.0
패키지 이름 : ClassPackage
패키지 버전 : 1.0.0
클래스 이름 : Board
해당 클래스 작성 : 최문석

해당 클래스 주요 기능
- 게시판의 모든 내용을 담아두는 클래스
- 게시판 탭의 테이블뷰를 만드는데도 사용

패키지 버전 변경 사항
 */

//게시물을 등록하거나 게시물 열람, 수정, 삭제 시에 사용하는 클래스
public class Board {
	private Integer boardNo;
	private String boardHeader;
	private String boardTitle;
	private String boardContent;
	private String boardPassword;
	private String boardUserNo;
	private String boardDate;
	private String boardFile;
	private Integer boardAvailable;

	public Board(Integer boardNo, String boardHeader, String boardTitle, String boardContent, String boardPassword,
			String boardUserNo, String boardDate, String boardFile, Integer boardAvailable) {
		this.boardNo = boardNo;
		this.boardHeader = boardHeader;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardPassword = boardPassword;
		this.boardUserNo = boardUserNo;
		this.boardDate = boardDate;
		this.boardFile = boardFile;
		this.boardAvailable = boardAvailable;
	}

	public Integer getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Integer boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardHeader() {
		return boardHeader;
	}

	public void setBoardHeader(String boardHeader) {
		this.boardHeader = boardHeader;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardPassword() {
		return boardPassword;
	}

	public void setBoardPassword(String boardPassword) {
		this.boardPassword = boardPassword;
	}

	public String getBoardUserNo() {
		return boardUserNo;
	}

	public void setBoardUserNo(String boardUserNo) {
		this.boardUserNo = boardUserNo;
	}

	public String getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}

	public String getBoardFile() {
		return boardFile;
	}

	public void setBoardFile(String boardFile) {
		this.boardFile = boardFile;
	}

	public Integer getBoardAvailable() {
		return boardAvailable;
	}

	public void setBoardAvailable(Integer boardAvailable) {
		this.boardAvailable = boardAvailable;
	}
}
