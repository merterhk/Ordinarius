<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body bgcolor="#ffdead">
    <form action="baglanti_arsa">

        <table border="5" width="1300" cellspacing="3" cellpadding="5" align="center">
            <tr>
                <td width="400">
                    &nbsp;&nbsp;<img src="resim\ev21.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\ev22.jpg" width="175" height="105"><br>
            &nbsp;&nbsp;<img src="resim\ev23.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\ev24.jpg" width="175" height="105" ><br>
            &nbsp;&nbsp;<img src="resim\ev17.jpg" width="175" height="105" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\ev26.jpg" width="175" height="105" ><br>
            &nbsp;&nbsp;<img src="resim\ev27.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\ev28.jpg" width="175" height="105">
            &nbsp;&nbsp;<img src="resim\ev29.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\ev13.jpg" width="175" height="105" >
            </td>
            <td ><center>İL:<select name="il">
                        <option>Adana</option>
                        <option>Adiyaman</option>
                        <option>Afyon</option>
                        <option>Agrı</option>
                        <option>Aksaray</option>
                        <option>Amasya</option>
                        <option>Ankara</option>
                        <option>Antalya</option>
                        <option>Ardahan</option>
                        <option>Artvin</option>
                        <option>Aydin</option>
                        <option>Balikesir</option>
                        <option>Batman</option>
                        <option>Bartin</option>
                        <option>Bayburt</option>
                        <option>Bilecik</option>
                        <option>Bingol</option>
                        <option>Bayburt</option>
                        <option>Bolu</option>
                        <option>Burdur</option>
                        <option>Bursa</option>
                        <option>Canakkale</option>
                        <option>Cankırı</option>
                        <option>Corum</option>
                        <option>Denizli</option>
                        <option>Diyarbakir</option>
                        <option>Duzce</option>
                        <option>Edirne</option>
                        <option>Elazig</option>
                        <option>Erzincan</option>
                        <option>Erzurum</option>
                        <option>Eskisehir</option>
                        <option>Gaziantep</option>
                        <option>Giresun</option>
                        <option>Gumushane</option>
                        <option>Hakkari</option>
                        <option>Hatay</option>
                        <option>Igdır</option>
                        <option>Isparta</option>
                        <option>Icel</option>
                        <option>Istanbul</option>
                        <option>Izmir</option>
                        <option>Kars</option>
                        <option>Kastamonu</option>
                        <option>Kayseri</option>
                        <option>Kirklareli</option>
                        <option>Kirikkale</option>
                        <option>Kirsehir</option>
                        <option>Kilis</option>
                        <option>Kocaeli</option>
                        <option>Kahramanmaras</option>
                        <option>Karabuk</option>
                        <option>Karaman</option>
                        <option>Konya</option>
                        <option>Kutahya</option>
                        <option>Malatya</option>
                        <option>Manisa</option>
                        <option>Mardin</option>
                        <option>Mugla</option>
                        <option>Mus</option>
                        <option>Nevsehir</option>
                        <option>Nigde</option>
                        <option>Ordu</option>
                        <option>Osmaniye</option>
                        <option>Rize</option>
                        <option>Sakarya</option>
                        <option>Samsun</option>
                        <option>Siirt</option>
                        <option>Sinop</option>
                        <option>Sivas</option>
                        <option>Tekirdag</option>
                        <option>Tokat</option>
                        <option>Trabzon</option>
                        <option>Tunceli</option>
                        <option>Sanliurfa</option>
                        <option>Sirnak</option>
                        <option>Usak</option>
                        <option>Van</option>
                        <option>Yozgat</option>
                        <option>Yalova</option>
                        <option>Zonguldak</option>
                    </select>
                    <br><br>
                    ARSA TİPİ:<select name="emlaktipi">
                        <option>Imarli Arsa</option>
                        <option>Konut Imarli </option>
                        <option> Arsa</option>
                        <option>Tarla</option>
                        <option> Ticari Imarli </option>
                        <option>Tarim Arazisi</option>
                        <option>Ticaret Alani</option>
                        <option> Ada </option>
                        <option>Ciftlik</option>
                        <option>Muhtelif Arsa</option>
                        <option>Sanayi Imarli</option>
                    </select>
                    <br><br><br>
                    EMLAK DURUMU:<select name="emlakdurumu">
                        <option>Satilik</option>
                        <option>Kiralik</option>
                    </select>
                    <br><br><br>
                    FİYAT ARALIĞI:<br><br>
                    MAX:<input type="text" name="max" value="" size="20" /><br><br>
                    MIN:<input type="text" name="min" value="" size="20" />
                </center>
            </td>
            <td width="400">
                &nbsp;&nbsp;<img alt="" src="resim\i1.jpg" width="175" height="110">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\a1.jpg" width="175" height="105"><br>
            &nbsp;&nbsp;<img src="resim\i2.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\a2.jpg" width="175" height="105" ><br>
            &nbsp;&nbsp;<img src="resim\i3.jpg" width="175" height="105" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\a3.jpg" width="175" height="105" ><br>
            &nbsp;&nbsp;<img src="resim\i4.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\a4.jpg" width="175" height="105">
            &nbsp;&nbsp;<img alt="" src="resim\i5.jpg" width="175" height="105">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <img src="resim\a5.jpg" width="175" height="105" >
            </td>
            </tr>
        </table><br><center>
            <input type="submit" value="ARA" name="submit" />
        </center>
        <a href="anasayfa.jsp"><b>ANASAYFA</b></a>
    </form>

</body>
</html>
