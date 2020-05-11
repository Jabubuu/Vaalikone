<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src= "scriptit.js"></script>
<meta charset="UTF-8">
<title>Lis�� ehdokas</title>


<link href="style.css" rel="stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div id="container">

<h2>Lis�� ehdokas</h2>
<form name="formi" action="#" method='post' onsubmit="return false">
<input id='etu' type='text' name='etu' value='' placeholder='Etunimi' onkeyup="checkform()" ><br>
<input id='suku' type='text' name='suku' value='' placeholder='Sukunimi' onkeyup="checkform()" ><br>
<input id='sala' type='text' name='sala' value='' placeholder='Salasana' onkeyup="checkform()" ><br>
<input id='tunnus' type='text' name='tunnus' value='' placeholder='K�ytt�j�tunnus' onkeyup="checkform()" ><br>
<input id='ika' type='text' name='ika' value='' placeholder='Ik�' onkeyup="checkform()" ><br>
<input id='puolue' type='text' name='puolue' value='' placeholder='Puolue' onkeyup="checkform()" ><br>
<input id='koti' type='text' name='koti' value='' placeholder='Kotipaikkakunta' onkeyup="checkform()" ><br>
<input id='ammatti' type='text' name='ammatti' value='' placeholder='Ammatti' onkeyup="checkform()" ><br>
<input id='miksi' type='text' name='miksi' value='' placeholder='Miksi eduskuntaan' onkeyup="checkform()" ><br>
<input id='mita' type='text' name='mita' value='' placeholder='Mit� haluaisit muuttaa' onkeyup="checkform()" ><br>


<input id='nappula' type='button' disabled="disabled" name='ok' value='Lis��' onclick='sendData();'><br> 
</form>

<p id='lisays'></p><br> 

<h2>Poista ehdokas</h2>
<form action="#" method='delete' onsubmit='return false;'>
<input id='eID' type='text' name='eID' value='' placeholder='Poistettavan ehdokkaan id'>
<input type='button' name='ok' value='Poista' onclick='deleteData();'><br>  </form>

<p id='poisto'></p><br> 

<h2>Hae ehdokas</h2>
<form action="#" method='get' onsubmit='return false;'>
<input id='haeID' type='text' name='haeID' value='' placeholder='Haettavan ehdokkaan id'>
<input type='button' name='ok' value='Hae' onclick='getOneData();'>  </form><br>

<button type="button" onclick="getData();">N�yt� kaikki ehdokkaat</button>

<p id='ehdokkaat'></p>

</div>
</body>
</html>