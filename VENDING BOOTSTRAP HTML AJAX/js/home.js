var moneyInMachine = 0;
$(document).ready(function (){
	clearItems();
	loadItems();
});
	
	$('#add-dollar-button').click(function (){
		
			moneyInMachine += 100;
			$('#total-money-in').val((moneyInMachine/100).toFixed(2));
			$('#message').val('');
			
			$('#change').val('');
			
    });
	
	$('#add-quarter-button').click(function(){
			moneyInMachine += 25;
			$('#total-money-in').val((moneyInMachine/100).toFixed(2));
			$('#message').val('');
			
			$('#change').val('');
	});
	
	
	$('#add-dime-button').click(function(){
			moneyInMachine += 10;
			$('#total-money-in').val((moneyInMachine/100).toFixed(2));
			$('#message').val('');
			
			$('#change').val('');
	});
	
	
	$('#add-nickel-button').click(function(){
			moneyInMachine +=5;
			$('#total-money-in').val((moneyInMachine/100).toFixed(2));
			$('#message').val('');
			
			$('#change').val('');
	});
	
	
	$('#change-return-button').click(function(){
		
		getChange();
		$('#total-money-in').val('');
		$('#message').val('');		
		
	});
	
	$('#make-purchase-button').click(function (){
        // $('#errorMessages').empty();
        // $('#show-message').empty();
		var id = $('#item').val();
		var money = $('#total-money-in').val();
	$.ajax({
        type: "GET",
        url: 'http://localhost:8080/money/' + money + '/item/' + id,
        dataType: 'json',
        success: function (data) {
			var quarters = data.quarters;
			var dimes = data.dimes;
			var nickels = data.nickels;
			
			var line = '';
			
			if(quarters > 0){
				line += quarters + ' quarter(s)' + ' ';
			}
			if(dimes > 0){
				line += dimes + ' dime(s)' + ' ';
			}
			if(nickels > 0){
				line += nickels + ' nickel(s)';
			}
			
			if(quarters == 0 && dimes == 0 && nickels == 0){
				line = 'No Change Returned';
			}
			
			$('#change').val(line);
			$('#message').val('Thanks for the money mah dude.');
			moneyInMachine = 0;
			$('#total-money-in').val('');
			line='';
			
			
			// getChange();
		
		// $('#make-purchase-button').click(function(){
			// if(item.quantity < 1){
				// $('#message').val('THERE IS NONE LEFT');
			// }
			// if(moneyInMachine < price){
				// difference = price - moneyInMachine;
				// $('#message').val('You need $' + difference + 'more.');
			// }
		// });
		clearItems();
		loadItems();
		},
		error: function(jqXHR,textStatus, errorThrown){
			
				var jsonError = $.parseJSON(jqXHR.responseText);
			$('#message').val(jsonError.message);
		
			// $('#errorMessages').append($('<li>'))
			// .attr({class: 'list-group-item list-group-item-danger'})
			// .text('Error calling web service. Please try again later.');
			}
			
});
});

function loadItems(){
	clearItems();
	
	
	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/items',
		success : function(data,status){
			$.each(data, function(index, item){
				
				var id = item.id;
				 var name = item.name;
				 var price = item.price;
				 var quantity = item.quantity;
				 
				var snack = '<div class="col-sm-4"><button style = "height: 200px; width:100%" id="'+id+'" onclick="selectThisId('+id+');">';
			
				 snack += '<p>#'+id+'</p>';
				 snack += '<p>'+name+'</p>';
				 snack += '<p>$'+price.toFixed(2)+'</p>';
				 snack += '<p>QuantityLeft: '+quantity+'</p>';
				 
				 snack += '</button></div>'
				 
				 $('#items').append(snack);
				 
			
			});
		},
		error: function(jqXHR,textStatus, errorThrown){
			// $('#errorMessages')
				// .append($('<li>'))
		// .attr({class: 'list-group-item list-group-item-danger'})
			// .text('Error calling web service. Please try again later.');
		}
	});
					
	
};


function getChange(){
	var dollars = 0;
		var quarters = 0;
		var dimes = 0;
		var nickels = 0;
				
		if(moneyInMachine > 0){
			
		dollars = parseInt(moneyInMachine/100);
		updatedAmount = moneyInMachine - (dollars * 100);
		
		quarters = parseInt(updatedAmount/25);
		updatedAmount = moneyInMachine - (dollars * 100) - (quarters * 25);
		
		dimes = parseInt(updatedAmount/10);
		updatedAmount = moneyInMachine - (dollars * 100) - (quarters * 25) - (dimes * 10);
		
		nickels = parseInt(updatedAmount/5);
		updatedAmount = moneyInMachine - (dollars * 100) - (quarters * 25) - (dimes * 10) - (nickels * 5)
		
		
		
			$('#change').val(dollars + ' dollars ' + quarters + ' quarters ' + dimes + ' dimes ' + nickels + ' nickels');
			moneyInMachine = 0;
			$('#total-money-in').val('$' + (moneyInMachine/100).toFixed(2));
			$('#message').val('');
			
		} else{
			$('#change').val('THERE IS NO MONEY ENTERED');
		}
};


function clearItems(){
	$('#items').empty();
}
function selectThisId(id){
	$('#item').val(id);
}

