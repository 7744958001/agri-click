/**
 * Account Settings - Account
 */

'use strict';
$(document).ready(function() {
	getUserDetails();
});

var getUserDetails = function() {
	const jwtToken = localStorage.getItem("jwtToken");
	$.ajax({
		url: "/api/authentication/getUserDetails",
		type: "GET",
		headers: {
			"Authorization": "Bearer " + jwtToken
		},
		success: function(response) {
			$('#firstName').val(response.firstName);
			$('#lastName').val(response.lastName);
			$('#email').val(response.username);
			$('#password').val(response.password);
			$('#phoneNumber').val(response.phoneNumber);
			$('#uploadedAvatar').attr('src', response.userfilePath);
			alert("User details fetched!");
			console.log(response);
		},
		error: function(xhr) {
			alert("Failed to get user details: " + xhr.responseText);
		},
	});
}

document.addEventListener('DOMContentLoaded', function (e) {
  (function () {
    const deactivateAcc = document.querySelector('#formAccountDeactivation');

    // Update/reset user image of account page
    let accountUserImage = document.getElementById('uploadedAvatar');
    const fileInput = document.querySelector('.account-file-input'),
      resetFileInput = document.querySelector('.account-image-reset');

    if (accountUserImage) {
      const resetImage = accountUserImage.src;
      fileInput.onchange = () => {
        if (fileInput.files[0]) {
          accountUserImage.src = window.URL.createObjectURL(fileInput.files[0]);
        }
      };
      resetFileInput.onclick = () => {
        fileInput.value = '';
        accountUserImage.src = resetImage;
      };
    }
  })();
});

var saveUserDetails = function() {
	const jwtToken = localStorage.getItem("jwtToken");
	$.ajax({
		url: "/api/authentication/updateUserDetails",
		type: "GET",
		headers: {
			"Authorization": "Bearer " + jwtToken
		},
		success: function(response) {
			$('#firstName').val(response.firstName);
			$('#lastName').val(response.lastName);
			$('#email').val(response.username);
			$('#password').val(response.password);
			$('#phoneNumber').val(response.phoneNumber);
			$('#uploadedAvatar').attr('src', response.userfilePath);
			alert("User details fetched!");
			console.log(response);
		},
		error: function(xhr) {
			alert("Failed to get user details: " + xhr.responseText);
		},
	});
}
