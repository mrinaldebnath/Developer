<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<html>
<head>
<title>Interviews Interface</title>
<!-- <script type="text/javascript"
	src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script> -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>

<script>
	$(document).ready(function() {
		$("#insert").click(function() {
			
			var scoreConatiner = document.getElementById("score");
			var commentConatiner = document.getElementById("comment");
			var score = scoreConatiner.value;
			var comment = commentConatiner.value;

			var dataToSend = {
				score : score,
				comment : comment
			};
			var index = $.ajax({
				type : "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				async : !1,
				url : "http://localhost:8080/DevelopersInfo/data", //assuming your controller is configured to accept requests on this URL
				data : JSON.stringify(dataToSend), // This converts the payLoad to Json to pass along to Controller
				success : function(result) {
					return result;
					// do what ever you want with data
				}
			});

			var buttEdit = document.createElement('input'); // create a button
			buttEdit.setAttribute('type', 'button'); // set attributes ...
			buttEdit.setAttribute('name', index.responseText);
			buttEdit.setAttribute('id1', index.responseText);
			buttEdit.setAttribute('score', score);
			buttEdit.setAttribute('comment', comment);
			buttEdit.setAttribute('value', "Edit");
			buttEdit.addEventListener('click', function() {
				editRow(this);
			});

			var buttDel = document.createElement('input'); // create a button
			buttDel.setAttribute('type', 'button'); // set attributes ...
			buttDel.setAttribute('name', index.responseText);
			buttDel.setAttribute('id1', index.responseText);
			buttDel.setAttribute('value', "Delete");
			buttDel.addEventListener('click', function() {
				deleteRow(this);
			});

			var table = document.getElementById("messages");
			var row = table.insertRow(table.rows.length);
			row.setAttribute('id', index.responseText);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			var cell4 = row.insertCell(3);
			var cell5 = row.insertCell(4);
			cell1.innerHTML = index.responseText;
			cell2.innerHTML = score;
			cell3.innerHTML = comment;
			cell4.appendChild(buttEdit);
			cell5.appendChild(buttDel);
			
			
			scoreConatiner.value = "";
			commentConatiner.value = "";

		});

	});

	var deleteRow = function(object) {

		var dataToSend = object.getAttribute('id1');

		$.ajax({
			type : "POST",
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			async : !1,
			url : "http://localhost:8080/DevelopersInfo/datadelete", //assuming your controller is configured to accept requests on this URL
			data : dataToSend, // This converts the payLoad to Json to pass along to Controller
			success : function(result) {

				// do what ever you want with data
			}
		});

		$(object).closest('tr').remove();

	};

	var editRow = function(object) {

		var popup = document.getElementById("myPopup");
		popup.classList.toggle("show");
		

		var id = object.getAttribute('id1');
		var score = object.getAttribute('score');
		var comment = object.getAttribute('comment');

		document.getElementById("idlabel").value = id;
		document.getElementById("scoreedit").value = score;
		document.getElementById("commentedit").value = comment;

	};

	var updateafterclick = function() {

		var id = document.getElementById("idlabel").value;
		var score = document.getElementById("scoreedit").value;
		var comment = document.getElementById("commentedit").value;

		if (id) {

			var dataToSend = {
				id : id,
				score : score,
				comment : comment
			};

			$.ajax({
				type : "POST",
				contentType : 'application/json; charset=utf-8',
				dataType : 'json',
				url : "http://localhost:8080/DevelopersInfo/dataedit", //assuming your controller is configured to accept requests on this URL
				data : JSON.stringify(dataToSend), // This converts the payLoad to Json to pass along to Controller
				success : function(result) {

					// do what ever you want with data
				}
			});

			var popup = document.getElementById("myPopup");
			popup.classList.toggle("show");

			var editrow = document.getElementById("messages").rows
					.namedItem(id);

			var buttEdit = document.createElement('input'); // create a button
			buttEdit.setAttribute('type', 'button'); // set attributes ...
			buttEdit.setAttribute('name', id);
			buttEdit.setAttribute('id1', id);
			buttEdit.setAttribute('score', score);
			buttEdit.setAttribute('comment', comment);
			buttEdit.setAttribute('value', "Edit");
			buttEdit.addEventListener('click', function() {
				editRow(this);
			});

			var buttDel = document.createElement('input'); // create a button
			buttDel.setAttribute('type', 'button'); // set attributes ...
			buttDel.setAttribute('name', id);
			buttDel.setAttribute('id1', id);
			buttDel.setAttribute('value', "Delete");
			buttDel.addEventListener('click', function() {
				deleteRow(this);
			});

			var cell1 = editrow.cells[0];
			var cell2 = editrow.cells[1];
			var cell3 = editrow.cells[2];
			editrow.deleteCell(4);
			editrow.deleteCell(3);
			
			
			cell1.innerHTML = id;
			cell2.innerHTML = score;
			cell3.innerHTML = comment;
			var cell4 = editrow.insertCell(3);
			var cell5 = editrow.insertCell(4);
			cell4.appendChild(buttEdit);
			cell5.appendChild(buttDel);

		}

		else
			alert("ID must be set");

	};
	
	var clicksortbyscore = function() {
			
			
			var home = document.getElementById("index");
			var dataToSend = home.getAttribute('toggled');

			var interviewList = $.ajax({
				type : "POST",
				contentType : 'text/plain',
				dataType : 'json',
				async : !1,
				url : "http://localhost:8080/DevelopersInfo/datasort", //assuming your controller is configured to accept requests on this URL
				data : dataToSend, // This converts the payLoad to Json to pass along to Controller
				success : function(result) {
					return result;
					// do what ever you want with data
				}
			});
			
			if(dataToSend == "no")home.setAttribute('toggled', "yes");
			else home.setAttribute('toggled', "no");
			
			var interviewListJSON = JSON.parse(interviewList.responseText);

			var totalRow = interviewListJSON.length;
			
			
			var table = document.getElementById("messages")
			while (table.firstChild) {
				table.removeChild(table.firstChild);
			}
			
			
			var row1 = table.insertRow(table.rows.length);
			var cell11 = row1.insertCell(0);
			var cell21 = row1.insertCell(1);
			var cell31 = row1.insertCell(2);    
			
			var buttNewSort = document.createElement('input'); // create a button
			buttNewSort.setAttribute('type', 'button'); // set attributes ...
			buttNewSort.setAttribute('name', "sortbyscore");
			buttNewSort.setAttribute('id', "sortbyscore");
			buttNewSort.setAttribute('value', "Sort score");
			buttNewSort.addEventListener('click', function() {
				clicksortbyscore();
			});
			
			cell21.appendChild(buttNewSort);
			
			var row2 = table.insertRow(table.rows.length);
			var cell12 = row2.insertCell(0);
			var cell22 = row2.insertCell(1);
			var cell32 = row2.insertCell(2);    
			cell12.innerHTML = "ID";
			cell22.innerHTML = "Score";
			cell32.innerHTML = "Comment";
			

			for (i = 0; i < totalRow; i++) {
				
				var id = interviewListJSON[i].id;
				var score = interviewListJSON[i].score;
				var comment = interviewListJSON[i].comment;
				
				var buttEdit = document.createElement('input'); // create a button
				buttEdit.setAttribute('type', 'button'); // set attributes ...
				buttEdit.setAttribute('name', id);
				buttEdit.setAttribute('id1', id);
				buttEdit.setAttribute('score', score);
				buttEdit.setAttribute('comment', comment);
				buttEdit.setAttribute('value', "Edit");
				buttEdit.addEventListener('click', function() {
					editRow(this);
				});
				
				

				var buttDel = document.createElement('input'); // create a button
				buttDel.setAttribute('type', 'button'); // set attributes ...
				buttDel.setAttribute('name', id);
				buttDel.setAttribute('id1', id);
				buttDel.setAttribute('value', "Delete");
				buttDel.addEventListener('click', function() {
					deleteRow(this);
				});

				var row = table.insertRow(table.rows.length);
				row.setAttribute('id', id);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				cell1.innerHTML = id;
				cell2.innerHTML = score;
				cell3.innerHTML = comment;
				cell4.appendChild(buttEdit);
				cell5.appendChild(buttDel);
				
			}

	};
</script>

<style type="text/css">
/* Popup container - can be anything you want */
.popup {
	position: relative;
	display: inline-block;
	cursor: pointer;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* The actual popup */
.popup .popuptext {
	visibility: hidden;
	width: 160px;
	background-color: #555;
	color: #fff;
	text-align: center;
	border-radius: 6px;
	padding: 8px 0;
	position: absolute;
	z-index: 1;
	bottom: 125%;
	left: 50%;
	margin-left: 40px;
}

/* Toggle this class - hide and show the popup */
.popup .show {
	visibility: visible;
	-webkit-animation: fadeIn 1s;
	animation: fadeIn 1s;
}

/* Add animation (fade in the popup) */
@
-webkit-keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}

}
@
keyframes fadeIn {
	from {opacity: 0;
}

to {
	opacity: 1;
}
}
</style>


</head>
<body>
	<br>

	<br>

	<br>

	<br>
	<div>
		<input type="number" id="score" name="score" placeholder="Score" />

		<div id="txtScore"></div>
	</div>

	<div>
		<input type="text" id="comment" name="comment" placeholder="Comment"/>
		<div id="txtHint"></div>
	</div>

	<button type="button" id="insert">Insert</button>
	<div id="nothing"></div>

	<button
		onclick="location.href = 'http://localhost:8080/DevelopersInfo/';"
		toggled="no" type="button" id="index">Home</button>

<div class="popup">
					<span class="popuptext" id="myPopup">

						<div>

							ID : <input type="number" id="idlabel" name="idlabel" />
							<div id="txtScoreEditLabel"></div>
						</div>

						<div>
							Edit score : <input type="number" id="scoreedit" name="scoreedit" />

							<div id="txtScoreEdit"></div>
						</div>

						<div>
							Edit comment : <input type="text" id="commentedit"
								name="commentedit" />
							<div id="txtHintEdit"></div>
						</div>

						<button type="button" id="update" onclick="updateafterclick()">Update</button>
						<div id="nothingedit"></div>

					</span>
				</div>

	<div id="nthing"></div>

	<table class="messages" id="messages" border="2">
		<tr>
			<td></td>
			<td><button type="button" id="sortbyscore" onclick="clicksortbyscore()">Sort score</button></td>
			<td></td>
		</tr>


		<tr>
			<td>ID</td>
			<td>Score</td>
			<td>Comment

				

			</td>



		</tr>
		<%
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String url = "jdbc:mysql://db4free.net/mrinaldb";
				String username = "mrinal";
				String password = "12345678";
				String query = "select * from interviews";
				Connection conn = DriverManager.getConnection(url, username, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while (rs.next()) {
					int getid = rs.getInt("ID");
					int score = rs.getInt("SCORE");
					String comment = rs.getString("COMMENT");
		%>
		<tr id=<%=getid%>>
			<td><%=getid%></td>
			<td><%=score%></td>
			<td><%=comment%></td>
			<td>
				<button id1=<%=getid%> score=<%=score%> comment=<%=comment%>
					onclick="editRow(this)">Edit</button>
			</td>
			<td>
				<button id1=<%=getid%> onclick="deleteRow(this)">Delete</button>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>

</body>