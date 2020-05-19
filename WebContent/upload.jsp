<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	<h1>File upload</h1>
	<br>
	<input type="file" id="selectedFile" onchange="uploadFile()">
	<img alt="Select image" id="img" src="resources/img/file.jpg" width="100px" height="100px">

</body>

<script type="text/javascript">
	function uploadFile() {
		var image = document.getElementById("img");
		var file = document.getElementById("selectedFile").files[0];

		var reader = new FileReader();
		reader.onloadend = function() {
			image.src = reader.result;
			$.ajax({
				method : "POST",
				url : "fileServlet",
				data : {
					fileUpload : image.src
				}
			}).done(function(response) {
				alert("sucess " + response);
			}).fail(function(xhr, status, error) {
				alert("error: " + xhr.responseText);
			});
		};
		if (file) {
			reader.readAsDataURL(file);
		} else {
			image.src = "resources/img/file.jpg";
		}
	}
</script>
</html>