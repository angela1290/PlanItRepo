// Select the elements
const clear = document.querySelector('.clear');
const deleteElement = document.getElementById('date');
const list = document.getElementById('list');
const input = document.getElementById('input');

// Class Names
const CHECK = 'fa-check-circle'; //Check button
const UNCHECK = 'fa-circle-thin'; //UnCheck button
const LINE_THROUGH = 'lineThrough'; // Done  - Completed or deleted task

let LIST, id;
// get items from local storage
let data = localStorage.getItem('TODO');

// Check if data is not empty
if (data) {
	LIST = JSON.parse(data);
	id = LIST.length; // set id to the last one in the list.
	loadList(LIST); // load the list to the user interface
} else {
	// there isn´t empty
	LIST = [];
	id = 0;
}

// Load the items to the users interface
function loadList(array) {
	array.forEach(function(item) {
		addToDo(item.name, item.id, item.done, item.trash);
	});
}
// Clear the local storage
clear.addEventListener('click', function() {
	localStorage.clear();
	location.reload();
});

//Dates
const options = { weekday: 'long', month: 'short', day: 'numeric' };
const today = new Date();

deleteElement.innerHTML = today.toLocaleDateString('en-US', options);

// Todo function

function addToDo(toDo, id, done, trash) {
	if (trash) {
		return;
	}

	const DONE = done ? CHECK : UNCHECK;
	const LINE = done ? LINE_THROUGH : '';

	const item = `  
            <li class="item">                                                 
            <i class="fa ${DONE} co" job="complete" id="${id}"></i>
			<p class="text ${LINE}">${toDo}</p>
			<i class="fa fa-trash-o de" job="delete" id="${id}"></i>                                                                             
			</li>    `;

	const position = 'beforeend';

	list.insertAdjacentHTML(position, item);

	//	addToDo('Drink Coffee');
}

// add item to list by enterKey

document.addEventListener('keyup', function(even) {
	if (event.keyCode == 13) {
		const toDo = input.value;

		// if input isnt emppty
		if (toDo) {
			addToDo(toDo, id, false, false);

			LIST.push({
				name: toDo,
				id: id,
				done: false,
				trash: false
			});

			//add items to local storage
			//(THIS CODE MUST BE ADDED EVERYWHERE THE LIST ARRAY IS UPDATED )
			localStorage.setItem('TODO', JSON.stringify(LIST));

			id++;
		}
		input.value = '';
	}
});

// Testing code defaulte...   addToDo('Coffee', 1, false, false);

// Complete todo

function completeTodo(element) {
	element.classList.toggle(CHECK);
	element.classList.toggle(UNCHECK);
	element.parentNode.querySelector('.text').classList.toggle(LINE_THROUGH);

	LIST[element.id].done = LIST[element.id].done ? false : true;
}

// Remove toDo

function removeToDo(element) {
	element.parentNode.parentNode.removeChild(element.parentNode);

	LIST[element.id].trash = true;
}

list.addEventListener('click', function(event) {
	const element = event.target; // return the clicked element inside list
	const elementJob = element.attributes.job.value; // complete of delete

	if (elementJob == 'complete') {
		completeTodo(element);
	} else if (elementJob == 'delete') {
		removeToDo(element);
	}
});
