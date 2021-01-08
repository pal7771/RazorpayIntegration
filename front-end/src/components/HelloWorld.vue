<template>
  <div>
    <div class="row">
      <p id="p1"></p>
      <button @click="submit()" type="submit"> Pay </button>

      <div v-if="this.razorpay_signature.length > 0">
        <h2>Succeeded</h2>
      </div>

      <p>{{this.paymentId}}</p>
    </div>
  </div>
</template>
<script>

import axios from 'axios';

export default {
  
  data: function() {
    return {
      name: '',
      quantity: '',
      amount: '',
      orderId: "",
      paymentId: "",
      razorpay_signature: ""
    }
  },
  methods : {
    submit(){
      axios.post('https://spring-razopay-backend.herokuapp.com/razorpay/order')
      .then(function (response) {

        console.log( response );

        let orderId = response.data;

        var options = {
          "key": "rzp_test_tlH7dlVx0y11uY",
          "amount": "50000",
          "currency": "INR",
          "name": "Acme Corp",
          "description": "Test Transaction",
          "image": "https://example.com/your_logo",
          "order_id": orderId,
          "handler": function (response){
          document.getElementById("p1").innerHTML = "Payment Succeded!";
          this.paymentId = response.razorpay_payment_id;
          let data = {
            paymentId: response.razorpay_payment_id,
            orderId: response.razorpay_order_id,
            signature: response.razorpay_signature,
            description: '',
            status: 'success',
            source: '',
            reason: '',
            step: ''
          }

          axios.post('https://spring-razopay-backend.herokuapp.com/razorpay/payment', data)
          .then(function (response) {
            this.$message({
              showClose: true,
              message: response,
              type: 'success'
            });
          }).catch(function (error) {
            console.error(error);
            this.$message({
              showClose: true,
              message: 'Oops, this is a error message.',
              type: 'error'
            });
          })


          },
          "prefill": {
              "name": "Gaurav Kumar",
              "email": "gaurav.kumar@example.com",
              "contact": "9999999999"
          }
        };
        
        var rzp1 = new window.Razorpay(options);

        rzp1.open();

        rzp1.on('payment.failed', function (response){

          document.getElementById("p1").innerHTML = "Payment Failed!";
          let data = {
            paymentId: response.error.metadata.payment_id,
            orderId: response.error.metadata.order_id,
            signature: '',
            description: response.error.description,
            status: 'failed',
            source: response.error.source,
            reason: response.error.reason,
            step: response.error.step
          }

          axios.post('https://spring-razopay-backend.herokuapp.com/razorpay/payment', data)
          .then(function (response) {
            console.log(response);
             this.$message({
              showClose: true,
              message: 'Warning, this is a warning message.',
              type: 'warning'
            });
          }).catch(function (error) {
            console.error(error);
            this.$message({
              showClose: true,
              message: 'Oops, this is a error message.',
              type: 'error'
            });
          })
        });

      })
      .catch(function (error) {
        console.log(error);
      });

    }
  }
}
</script>