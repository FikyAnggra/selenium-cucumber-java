Feature: Pencarian di Google

  Scenario: Mencari ChatGPT di Google
    Given Saya membuka halaman Google
    When Saya memasukkan "ChatGPT" ke dalam kolom pencarian
    And Saya menekan tombol pencarian
    Then Saya harus melihat hasil pencarian yang berhubungan dengan "ChatGPT"