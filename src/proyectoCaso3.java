import javax.swing.*;

public class proyectoCaso3 {
    public static void main(String[] args) {
        // Programa que simula funcionalidad un cajero automático de un cliente ficticio.

        // Datos registrados en programa Rut y PIN de acceso.

        String[] rutUsarios = {"1234-5","1111-1"};
        String[] pines = {"1234","4321"};
        int[] saldos = {10000,15000};

        String rut;
        String pin;
        int perfilUsuario = -1; // Variable array.

        // Inicio de sesión. Validación si el usuario ingresa mal su PIN 3 veces el programa terminara ingresando un error.

        int intentos = 3;
        while (intentos > 0 && perfilUsuario == -1) {
            rut = JOptionPane.showInputDialog(null,"Bienvenidos a ATM CIISA / Por favor, ingrese su RUT con guión");
            pin = JOptionPane.showInputDialog(null,"Ingrese su clave de acceso");

            for (int i = 0; i < rutUsarios.length; i++) {
                if (rutUsarios[i].equals(rut) && pines[i].equals(pin)) {
                    perfilUsuario = i;
                    break; // Termina el bucle for.
                }
            }

            if (perfilUsuario == -1) {
                intentos--;
                if (intentos > 0) {
                    JOptionPane.showMessageDialog(null, "RUT o PIN incorrecto. Intente nuevamente. Intentos restantes: " + intentos);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR! \nHa excedido el límite de intentos permitidos.");
                    return; // Termina la ejecución del programa.
                }
            }
        }

        // Menú de opciones el usuario podrá: ver saldo, abonar dinero, girar dinero, cambiar pin, salir.

        int opcion = 0;
        while (opcion != 5) {
            String opcionString = JOptionPane.showInputDialog(null, "MENÚ DE OPCIONES ATM CIISA" +
                    "\n1. Ver saldo" +
                    "\n2. Abonar dinero" +
                    "\n3. Girar dinero" +
                    "\n4. Cambiar PIN" +
                    "\n5. Salir" +
                    "\nIngrese una opción por favor:");
            opcion = Integer.parseInt(opcionString);

            switch (opcion) {
                case 1: // Ver saldo
                    JOptionPane.showMessageDialog(null, "Su saldo actual es de $" + saldos[perfilUsuario]);
                    break;
                case 2: // Abonar dinero con restricción no se podrá abonar un monto inferior a $1.
                    String montoAbonoString = JOptionPane.showInputDialog(null, "Ingrese el monto a abonar:");
                    int montoAbono = Integer.parseInt(montoAbonoString);

                    if (montoAbono <1) {
                        JOptionPane.showMessageDialog(null, "Error: El monto a abonar debe ser mayor o igual a $1.");
                    } else {
                        saldos[perfilUsuario] += montoAbono;
                        JOptionPane.showMessageDialog(null, "Abono exitoso. Su nuevo saldo es de $" + saldos[perfilUsuario]);
                    }

                    break;

                case 3: // Girar dinero con restricción no se podrá girar un monto inferior a $1 ni superior al saldo actual.
                    String montoGiroString = JOptionPane.showInputDialog(null, "Ingrese el monto a girar:");
                    int montoGiro = Integer.parseInt(montoGiroString);
                    int saldog;
                    saldog = saldos[perfilUsuario] - montoGiro;



                    if (montoGiro < 1 || montoGiro > saldos[perfilUsuario] ){
                        JOptionPane.showMessageDialog(null, "Error: Giro debe ser mayor o igual a $1 y menor o igual a su saldo actual ($" + saldos[perfilUsuario] + ").");
                    }else {
                        JOptionPane.showMessageDialog(null, "Abono exitoso. Su nuevo saldo es de $" + saldog);
                    }

                    break;
                case 4: // Cambio de PIN debe verificar el PIN actual y solicitar al usuario el nuevo 2 veces como confirmación, si esta falla el programa debe terminar indicando un error.

                    // Verificación de PIN actual
                    String pinActualString = JOptionPane.showInputDialog(null, "Ingrese su PIN actual:");
                    if (!pines[perfilUsuario].equals(pinActualString)) {
                        JOptionPane.showMessageDialog(null, "Error: PIN actual incorrecto.");
                        break;
                    }

                    // Nuevo PIN
                    String nuevoPin;
                    String confirmacionPin;
                    int intentosCambioPin = 2;
                    while (intentosCambioPin >= 1) {
                        nuevoPin = JOptionPane.showInputDialog(null, "Ingrese su nuevo PIN:");
                        confirmacionPin = JOptionPane.showInputDialog(null, "Confirme su nuevo PIN:");
                        if (nuevoPin.equals(confirmacionPin)) {
                            pines[perfilUsuario] = nuevoPin;
                            JOptionPane.showMessageDialog(null, "PIN cambiado exitosamente.");
                            break;
                        } else {
                            intentosCambioPin--;
                            if (intentosCambioPin >= 0) {
                                JOptionPane.showMessageDialog(null, "Error: Los PIN ingresados no coinciden. Intentos restantes: " + intentosCambioPin);
                            } if (intentosCambioPin<=0){
                                JOptionPane.showMessageDialog(null, "ERROR! \nHa excedido el número de intentos permitidos.");
                                return;



                                // Termina la ejecución del programa cajero automático
                            }


                        }
                    }
            }
        }
    }
}