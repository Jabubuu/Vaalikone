
function sendData(){

	var ehdokas=new Object();
	ehdokas.etunimi=document.getElementById("etu").value;
	ehdokas.sukunimi=document.getElementById("suku").value;
 	ehdokas.salasana=document.getElementById("sala").value;
	ehdokas.ika=document.getElementById("ika").value;
	ehdokas.puolue=document.getElementById("puolue").value;
	ehdokas.kotipaikkakunta=document.getElementById("koti").value;
	ehdokas.ammatti=document.getElementById("ammatti").value;
	ehdokas.miksiEduskuntaan=document.getElementById("miksi").value;
	ehdokas.mitaAsioitaHaluatEdistaa=document.getElementById("mita").value; 
	ehdokas.tunnus=document.getElementById("tunnus").value;

	
	var jsonEhdokas=JSON.stringify(ehdokas);
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	   var returned=JSON.parse(this.responseText);
	   document.getElementById("lisays").innerHTML="Lisätty ehdokas:" + "<br/>" + "ID = "+returned.ehdokasId+  "<br/>" +" Ehdokas = "+returned.etunimi+" "+returned.sukunimi;
	  }
	};
	
	xhttp.open("POST","./rest/ehdokasservice",true);
	xhttp.setRequestHeader("Content-type","application/json");
	xhttp.send(jsonEhdokas);
}

function deleteData(){
	
	var deleteId = document.getElementById("eID").value;
	var xhttp = new XMLHttpRequest();

	xhttp.onload = function () {
		var users = JSON.parse(xhttp.responseText);
		if (this.readyState == 4 && this.status == 200) {
			window.alert("Poistettu!");
		} else {
			window.alert("EI ONNNISTU!");
		}
	}
	xhttp.open("DELETE", "./rest/ehdokasservice/delete/"+deleteId, true);
	xhttp.send(null);
}

//    var xhttp = new XMLHttpRequest();
//
//    var eID = document.getElementById("eID").value;
//    xhttp.onreadystatechange = function() {
//      if (this.readyState == 4 && this.status == 200) {
//    	  window.alert("Poistettu!");
////    	  document.getElementById("poisto").innerHTML= "Ehdokas poistettu!";
//      }
//    };
//    xhttp.open("POST","./rest/ehdokasservice/delete/"+eID,true);
//    xhttp.send();
//    }

function getOneData(){
	
	var xhttp = new XMLHttpRequest();
	var haeid = document.getElementById("haeID").value;
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	   var json= JSON.parse(this.responseText);
	   var txt="";
	   var x;
	   for (x in json) {
		   txt += json[x].ehdokasId + ": " + json[x].etunimi + " " + json[x].sukunimi + "<br />"
		   + json[x].ammatti + "<br />" 
		   + json[x].puolue + "<br />" 
		   + json[x].kotipaikkakunta + "<br />" + "<br />"
		   + json[x].miksiEduskuntaan + "<br />" + "<br />"
		   + json[x].mitaAsioitaHaluatEdistaa + "<br />";
	   }
	   document.getElementById("ehdokkaat").innerHTML = txt;
	  }
	};
	xhttp.open("GET","./rest/ehdokasservice/"+haeid,true);
	xhttp.setRequestHeader("Content-type","application/json");
	xhttp.send();
	}
	
function getData(){
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	   var json= JSON.parse(this.responseText);
	   var txt="";
	   var x;
	   var num = 0;
	   for (x in json) {
		   txt += json[x].ehdokasId + ": " + json[x].etunimi + " " + json[x].sukunimi + "<input type='button' name='ok' value='Lisää' onclick='moreData(" + num + ");'>" +"<br />" + "<p id='moreEhd"+ num +"'></p>"+"<br />";		   		   
		   num++;		   
	   }

	   document.getElementById("ehdokkaat").innerHTML = txt;
	  }
	};
	xhttp.open("GET","./rest/ehdokasservice/all",true);
	xhttp.setRequestHeader("Content-type","application/json");
	xhttp.send();
	}
	
function moreData(num){
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	   var json= JSON.parse(this.responseText);
	   var txt=json[num].etunimi + json[num].sukunimi + "<br />" 
			   + json[num].ika + "<br />"
			   + json[num].ehdokasId + "<br />"
			   + json[num].puolue + "<br />" 
			   + json[num].kotipaikkakunta + "<br />"
			   + json[num].miksiEduskuntaan + "<br />"
			   + json[num].mitaAsioitaHaluatEdistaa + "<br />"
			   + json[num].ammatti + "<br />"
			   + "<input type='button' name='ok' value='Vähemmän' onclick='lessData(" + num + ");'>" +"<br />";
	   document.getElementById("moreEhd" + num).innerHTML = txt;
	  }
	};
	xhttp.open("GET","./rest/ehdokasservice/all",true);
	xhttp.setRequestHeader("Content-type","application/json");
	xhttp.send();
	}
	
function lessData(num){
	
	var xhttp = new XMLHttpRequest();
	
	xhttp.onreadystatechange = function() {
	  if (this.readyState == 4 && this.status == 200) {
	   var json= JSON.parse(this.responseText);
	   var txt=""; 
	   document.getElementById("moreEhd" + num).innerHTML = txt;
	  }
	};
	xhttp.open("GET","./rest/ehdokasservice/all",true);
	xhttp.setRequestHeader("Content-type","application/json");
	xhttp.send();
	}

function checkform()
{
    var f = document.forms["formi"].elements;
    var cansubmit = true;

    for (var i = 0; i < f.length; i++) {
        if (f[i].value.length == 0) 
        cansubmit = false;
    }

   	document.getElementById('nappula').disabled = !cansubmit;
    
}
	window.onload = checkform;
	
	function myFunction() {
		var x = document.getElementById("login2");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
	

