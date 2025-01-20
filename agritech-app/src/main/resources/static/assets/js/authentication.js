$(document).ready(function () {
      $("#create-account-submit-button").click(function () {
        let isValid = true;

        // Reset error messages
        $(".text-danger").addClass("d-none");

        // Validate email
        const email = $("#username").val();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
          $("#username-error").removeClass("d-none");
          isValid = false;
        }

        // Validate password
        const password = $("#password").val();
        if (password.length < 6) {
          $("#password-error").removeClass("d-none");
          isValid = false;
        }

        // Validate terms and conditions
        if (!$("#terms-conditions").is(":checked")) {
          $("#terms-error").removeClass("d-none");
          isValid = false;
        }

        // If all validations pass, make API call
        if (isValid) {
          const requestData = {
            username: email,
            password: password,
          };

          $.ajax({
            url: "/api/authentication/register",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(requestData),
            success: function (response) {
              alert("Registration successful!");
              // Redirect or perform any further actions
            },
            error: function (xhr) {
              alert("Registration failed: " + xhr.responseText);
            },
          });
        }
      });
      
      //user login
 const BASE_URL = "http://localhost:8081/auth";
 function validateForm() {
        let isValid = true;
        const email = $('#email').val().trim();
        const password = $('#password').val().trim();

        if (email === '' || !/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+$/.test(email)) {
          $('#emailError').removeClass('d-none');
          isValid = false;
        } else {
          $('#emailError').addClass('d-none');
        }

        if (password === '' || password.length < 6) {
          $('#passwordError').removeClass('d-none');
          isValid = false;
        } else {
          $('#passwordError').addClass('d-none');
        }

        return isValid;
      }

      $('#loginButton').on('click', function () {
        if (validateForm()) {
			const username = $('#email').val().trim();
        	const password = $('#password').val().trim();
          $.ajax({
            url: "/auth/login",
            type: 'POST',
            contentType: 'application/json',
          	data: JSON.stringify({ username, password }),
            success: function (token) {
				alert(token);
                console.log("JWT Token:", token);
                localStorage.setItem("jwtToken", token);
                alert("Login successful! Token stored.");
            },
            error: function (error) {
              alert('Login failed. Please try again.');
            }
          });
        }
      });
        
    });