/**
 * 
 */
function callStatement() {
	const request = new XMLHttpRequest();
	let accountId = $('#accountId').val();
	let fromdate = $('#fromDate').val();
	let toDate = $('#toDate').val();
	let fromAmount = $('#fromAmount').val();
	let toAmount = $('#toAmount').val();
	let url = "http://localhost:8080/statements?accountId="+accountId+"&fromDate="+fromdate+"&toDate="+toDate+"&fromAmount="+fromAmount+"&toAmount="+toAmount;
	
	request.open("get",url);
	request.send();
	request.onload = ()=>{
		$("#response").val(request.response)	
	}
}

function clearall() {
	$('#accountId').val('')
	$('#fromDate').val('');
	$('#toDate').val('');
	$('#fromAmount').val('');
	$('#toAmount').val('');
	$("#response").val('')
}