
$(document).ready(function() {
	$("#languageDropdownMenuButton a").click(function(e) {
		e.preventDefault(); // cancel the link behaviour
		var languageSelectedText = $(this).text();
		var languageSelectedValue = $(this).attr("value");
		$("#btnLanguage").text(languageSelectedText);
		window.location.replace('?lang=' + languageSelectedValue);
		return false;
	});
});

$(document).ready(function() {
	$("#btnEnglish").click(function(e) {
		e.preventDefault(); // cancel the link behaviour
		var languageSelectedValue = $(this).attr("value");
		window.location.replace('?lang=' + languageSelectedValue);
		return false;
	});
});

$(document).ready(function() {
	$("#btnSpanish").click(function(e) {
		e.preventDefault(); // cancel the link behaviour
		var languageSelectedValue = $(this).attr("value");
		window.location.replace('?lang=' + languageSelectedValue);
		return false;
	});
});