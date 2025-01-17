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
            url: "/api/login", // Replace with your Spring Boot API endpoint
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
      
    });