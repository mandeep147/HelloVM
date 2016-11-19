import java.net.URL;

import com.vmware.vim25.VirtualMachineCapability;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.mo.*;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;

public class HelloVM 
{
  public static void main(String[] args) throws Exception
  {
    long start = System.currentTimeMillis();
    ServiceInstance si = new ServiceInstance(new URL("https://130.65.159.14/sdk/"), "cmpe281_sec3_student@vsphere.local", "cmpe-LXKN", true);
    long end = System.currentTimeMillis();
    System.out.println("time taken:" + (end-start));
    
    Folder rootFolder = si.getRootFolder();
    String name = rootFolder.getName();
    System.out.println("root:" + name);
    
    ManagedEntity[] mes = new InventoryNavigator(rootFolder).searchManagedEntities("VirtualMachine");
    
    if(mes == null || mes.length == 0)
    {
      return;
    }
    
    VirtualMachine vm = (VirtualMachine) mes[0]; 
    
    VirtualMachineConfigInfo vminfo = vm.getConfig();
    VirtualMachineCapability vmc = vm.getCapability();

    vm.getResourcePool();
    System.out.println("Hello " + vm.getName());
    System.out.println("GuestOS: " + vminfo.getGuestFullName());
    System.out.println("Multiple snapshot supported: " + vmc.isMultipleSnapshotsSupported());

    si.getServerConnection().logout();
  }

}
