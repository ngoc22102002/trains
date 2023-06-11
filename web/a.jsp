<!DOCTYPE html>
<html>
    <head>
        <style>
            .train {
                display: flex;
                flex-wrap: nowrap;
                justify-content: center;
                align-items: center;
            }

            .carriage {
                width: 100px;
                height: 50px;
                background-color: #ccc;
                margin: 10px;
            }
        </style>
    </head> 
    <body>
        <div class="train">
            <div class="carriage"></div>
            <div class="carriage"></div>
            <div class="carriage"></div>
        </div>

        <script>
            const buttons = document.querySelectorAll('.button');
            const boxes = document.querySelectorAll('.box');
            buttons.forEach((button, index) => {
                button.addEventListener('click', () => {

                    boxes.forEach((box) => {
                        box.style.display = 'none';
                    });


                    boxes[index].style.display = 'block';
                });
            });
        </script>

    </body>
</html>