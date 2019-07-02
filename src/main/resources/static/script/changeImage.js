function changeImage(imgCount, dir) {
	var randomCount = Math.round(Math.random() * (imgCount - 1));
	document.getElementById("image").style.backgroundImage = "url(" + dir
			+ randomCount + ".jpg)";
}