<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Progress</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
#background {
	width: 90%;
	background-color: gray;
}

#bar {
	width: 0%;
	height: 30px;
	background-color: green;
}

.ui-progressbar {
	width: 90%;
}
</style>
</head>
<body>
	<h1>Progress bar with JS</h1>
	<br>
	<div id="background">
		<div id="bar"></div>
	</div>
	<br>
	<button onclick="startJSBar()">Start progress bar</button>
	<br>
	<h1>Progress bar with JQuery</h1>
	<button onclick="startJQueryBar()">Start progress bar after 2 seconds</button>
	<br>
	<br>
	<div id="progressbar">
		<div class="progress-label">Loading...</div>
	</div>
	<br>
</body>
<script type="text/javascript">
	function startJSBar() {
		var i = 0;
		var idTimer = setInterval(addProgress, 100);
		function addProgress() {
			if (i <= 100) {
				document.getElementById('bar').style.width = i + '%';
				i++;
			} else {
				clearInterval(idTimer);
			}
		}
	}
	function startJQueryBar() {
		$(function() {
			var barid = $("#progressbar"), barlabel = $(".progress-label");
			barid.progressbar({
				value : false,
				change : function() {
					barlabel.text(barid.progressbar('value') + "%");
				},
				complete : function() {
					barlabel.text('Complete!');
				}
			});

			function progress() {
				var barvalue = barid.progressbar("value") || 0;
				barid.progressbar("value", barvalue + 2);
				if (barvalue < 99) {
					setTimeout(progress, 80);
				}
			}
			setTimeout(progress, 2000);
		});
	}
</script>
</html>