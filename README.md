# wantOfficeMain

# 1. git 사용 Rule

1. main 에 직접 push 하지 않는다.
2. 각각 기능별로 브랜치를 생성한다. [ 브랜치명은 어떤 기능 구현인지가 담기도록 설정! ]
3. 코드작업 진행 작업 디렉토리 최대한 디테일하게 분리하기
4. 코드 작업이 끝난 후 commit > main pull 받기(두번째 pull 사용) > 충돌 발생시 충돌해결 > push
- 커밋메세지 : [본인이름] 어떤 내용의 수정인지 디테일하게
- 충돌을 해결하지 못하고 push를 했을 경우 git에서 병합 전 형상관리자가 충돌 해결 후 병합 진행
1. 병합 주기에 맞춰서 각각의 push 된 브랜치 main과 병합 진행 git에서 각각의 브랜치를 main과 병합하는 작업은 형상관리자가 진행 
   개인이 추가 병합이 필요할 경우 형상관리자에게 요청 병합 완료 후 브랜치 삭제 진행
- 병합주기 : 매일 하루 한번 오전 9시30분 진행
- 추가 작업 후 병합 시 형상관리자 님과 이야기 후 병합 및 슬랙 병합되었는지 공지
- `코드 작업 후 오류가 없을 시만 Push해주세요!!!!!!!!!`

# ★다같이 한번씩 주의 해주세요!!

1. commit, push 혹시 충돌 해결이 어렵거나 잘 모를 경우 임의대로 무조건 push 하지말고 조원들의 help를 구하기!! `main에서 작업 절대금지!!`
2. git Repositories 늘 켜놓고 어디서 작업 중인지 수시로 확인, commit push 전 내가 만든 브랜치에서 작업한 게 맞는지 다시 한번 `doubleCheck`
3. 각자 열심히 작업한 코드가 날라가지 않게 `pull, push 할땐 항상 주의하기!`

# 2. 코드 작업시 폴더 생성 Rule
사용자 화면은 user 폴더 하위에서 작업진행

예시)
- user 안에서 결제 로직 작업 시 WantOffice > user > payment > controller,dto,repository,entity,service > 하위 클래스들 생성
- user 안에서 회원 로직 작업 시 WantOffice > user > member > controller,dto,repository,entity,service > 하위 클래스들 생성
