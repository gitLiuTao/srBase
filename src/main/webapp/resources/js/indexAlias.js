function tologinAlias(){
//	alert($("#lgform1").attr("class"));
	$("#lgform1").submit();
}

function getatrest(purl){
	$.ajax({
		   type: "get",
		   url: purl,
		   data: "",
		   success: function(msg){
		    // alert( "Data Saved: " + msg );
			   $('#showinfo').modal('show');
			   $('#mt_infobody').html(JSON.stringify(msg));
		   }
		});
}

function getat2(){
}