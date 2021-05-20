/**
 * Confirm before Client side redirect
 */
function confirmGo(msg,url) {
    if ( confirm(msg) ) {
        window.location = url;
    }
}
/*
	Dynamically Append Error Messages
*/
const appendError = (controlName, innerHTML) => {
	 	
			 var controlElement = document.getElementById(controlName);
			 var span = document.createElement("span");
			 span.innerHTML = innerHTML;
			 controlElement.parentNode.insertBefore( span, controlElement.nextSibling );
 		}
	
/*
	Insert Top Corner Message
*/ 
	 const insertMessage = (Message, ErrBoolean) => {
		 let msgobj = document.createElement("div");
         msgobj.setAttribute("id", "CtlMsg");
         if (!ErrBoolean) msgobj.setAttribute("class", "CtlMsg success"); else msgobj.setAttribute("class", "CtlMsg fail");
         msgobj.innerHTML = Message;
		 document.body.appendChild(msgobj);
		 removeSlowly("CtlMsg");
	 }
        

/*
	Fade Out Animation used mainly for Message
*/
	 const removeSlowly = controlName => {
		 	let obj = document.getElementById(controlName);
		 	obj.style.opacity =  1;
		 	setTimeout(() => {
		 		let fx = setInterval(() => {
		        	if (obj.style.opacity != 0) { 
		        		obj.style.opacity -=  0.1;
    				} else {
	    				clearInterval(fx);
			        	obj.remove();    
			        }
				}, 50);	
			}, 1000);     
	 } 

	 
let allItems = new Map()


let loadedItems = new Map()
 
 const test = () => {
    e.preventDefault();
 } 


 const listen = (e) => {
    if (e.keyCode == 13) {
         e.preventDefault();
        findbarcode();

    }
} 

const increase = (id) => {
    let input;
    if (document.getElementById("action").checked) {
        input= document.querySelector(".rmdT tbody #qty_Load" + id);
    } else {
        input = document.querySelector(".rmdT tbody #qty_Return" + id);
    }
    if (Number(input.value) < Number(input.max)) 
        input.value =  Number(input.value) + 1; 
}

const decrease = (id) => {
    let input;
    if (document.getElementById("action").checked) {
        input= document.querySelector(".rmdT tbody #qty_Load" + id);
    } else {
        input = document.querySelector(".rmdT tbody #qty_Return" + id);
    }
    if (Number(input.value) > 1)
    input.value =  Number(input.value) -1; 
}

const deleteRow = (e) => {
    let row = e.target.parentElement.parentElement;
    let tbody = row.parentElement;
    let scanDiv = tbody.parentElement.parentElement;
    
    tbody.removeChild(row);
    if (!tbody.querySelector("td")) scanDiv.classList.add("hidden")
    
    
}

const insertReturn = (barcode) => {
    let txtbox = document.getElementById("barcode");
    txtbox.value = barcode;
    document.getElementById("action").checked = false;
    findbarcode();

}

const findbarcode = () => {
    
    let action = document.getElementById("action").checked;
    let itemMap;
    let table;
    let postfix;
    if (!action) {
        //return items
        table = document.querySelector("#returnItems tbody");
        itemMap = loadedItems;
        postfix = "Return"
    } else {
        //Load Items
        table = document.querySelector("#loadItems tbody");
        itemMap = allItems;
        postfix = "Load"
    }

    let lblError = document.getElementById("error");
    let txtbox = document.getElementById("barcode");
    lblError.innerText = "";

    if (!txtbox.value) {
        lblError.innerText = "Barcode Field is empty";
        return;
    }

    let element = itemMap.get(txtbox.value.trim()) ;
    if (element) { 
        table.parentElement.parentElement.classList.remove("hidden");

        let checkExist = table.querySelector("#td_" + element.itemID)
        if (checkExist) {

            if (!element.multibarcode) increase(element.itemID); else lblError.innerText="Item already added."
            txtbox.focus();

        } else {
            let tblRow = document.createElement("tr");  
            tblRow.setAttribute("id", "td_" + element.itemID )
                let tdID = document.createElement("td");
                    tdID.innerHTML = element.itemID;
                    let inputID = document.createElement("input");
                    inputID.setAttribute("type", "hidden");
                    inputID.setAttribute("id", "id_"+element.itemID);
                    inputID.setAttribute("name", "barcodes" + postfix);
                    inputID.setAttribute("value", element.itemID);    
                    tdID.appendChild(inputID)
                tblRow.appendChild(tdID);
                
                let tdName = document.createElement("td")
                    let nameRowColSpan = element.multibarcode?2:1;
                    tdName.innerHTML = element.name;
                    tdName.colSpan = nameRowColSpan
                tblRow.appendChild(tdName);    
                if (!element.multibarcode) {
                    let tdQty = document.createElement("td");
                    
                            let btnQtyDec = document.createElement("button");
                            btnQtyDec.setAttribute("type", "button");
                            btnQtyDec.classList.add("qtyBtn")
                            btnQtyDec.setAttribute("onclick", "decrease("+element.itemID+")");
                            btnQtyDec.innerHTML = "<i class=\"fas fa-minus\"></i>"
                        tdQty.appendChild(btnQtyDec)

                            let inputQty = document.createElement("input");
                            inputQty.setAttribute("type", "number");
                            inputQty.setAttribute("min", "1");
                            if (action) 
                                inputQty.setAttribute("max", element.quantityLeft);
                            else 
                                inputQty.setAttribute("max", element.quantity);
                            inputQty.setAttribute("id", "qty_"+ postfix +element.itemID);
                            inputQty.setAttribute("name", "qty_"+ postfix +element.itemID);
                            
                            if (action)
                                inputQty.setAttribute("value", 1);    
                            else
                                inputQty.setAttribute("value", element.quantity);    
                        tdQty.appendChild(inputQty)

                            let btnQtyInc = document.createElement("button");
                            btnQtyInc.setAttribute("type", "button");
                            btnQtyInc.classList.add("qtyBtn")
                            btnQtyInc.setAttribute("onclick", "increase("+element.itemID+")");
                            btnQtyInc.innerHTML = "<i class=\"fas fa-plus\"></i>"
                        tdQty.appendChild(btnQtyInc)
                    tblRow.appendChild(tdQty);
                }
                
                let tdDelete = document.createElement("td");
                        let btnDelete = document.createElement("i");
                        btnDelete.setAttribute("type", "button");
                        btnDelete.classList.add("fas")
                        btnDelete.classList.add("fa-trash-alt")
                        btnDelete.setAttribute("onclick", "deleteRow(event)");
                    tdDelete.appendChild(btnDelete)
                tblRow.appendChild(tdDelete);
            table.appendChild(tblRow);              
        }
        txtbox.value = "";
    
    txtbox.focus();
        
    } else {
        if (txtbox.value) {
            lblError.innerText = "Barcode not found";
            txtbox.value = "";
            return;
        }
    }
}
	 
	 
	 
