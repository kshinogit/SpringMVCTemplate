// リンククリック時にメイン部を更新する処理
document.querySelectorAll('.content-link').forEach(function(link) {
  link.addEventListener('click', function(e) {
    e.preventDefault();
    var linkText = this.textContent.trim();
    document.getElementById('mainContentTitle').textContent = linkText;

    if (window.innerWidth <= 768) {
      document.getElementById('sidebar').classList.remove('open');
    }
  });
});

// モバイル用トグルボタンでサイドバーの表示/非表示を切り替え
document.getElementById('menuToggle').addEventListener('click', function() {
  document.getElementById('sidebar').classList.toggle('open');
});
