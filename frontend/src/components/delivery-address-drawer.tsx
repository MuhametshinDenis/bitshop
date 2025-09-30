import {
  Drawer,
  DrawerClose,
  DrawerContent,
  DrawerDescription,
  DrawerFooter,
  DrawerHeader,
  DrawerTitle,
  DrawerTrigger,
} from "@/components/ui/drawer"
import { Navigation } from "lucide-react"
import { Button } from "@/components/ui/button"
import { User } from "@/types/user"
import { Input } from "@/components/ui/input"

export function DeliveryAddressDrawer({ user }: { user: User | null }) {
  return (
    <Drawer>
      <DrawerTrigger asChild>
        <div
          role="button"
          className="flex items-center gap-2 text-muted-foreground transition-all duration-200 hover:cursor-pointer hover:text-primary"
        >
          <Navigation size={20} />
          {user?.deliveryAddress ? user.deliveryAddress : "Выбрать адрес"}
        </div>
      </DrawerTrigger>
      <DrawerContent>
        <DrawerHeader>
          <DrawerTitle>
            {user?.deliveryAddress
              ? "Изменить адрес доставки"
              : "Добавить адрес доставки"}
          </DrawerTitle>
          <DrawerDescription>
            {user?.deliveryAddress
              ? "Введите новый адрес, чтобы обновить текущий адрес доставки."
              : "Введите адрес доставки, чтобы его сохранить для заказов."}
          </DrawerDescription>
        </DrawerHeader>
        <div className="px-4">
          <Input
            placeholder="Введите новый адрес доставки"
            className="text-sm"
          />
        </div>
        <DrawerFooter>
          <Button>Сохранить</Button>
          <DrawerClose asChild>
            <Button variant="outline" className="w-full">
              Отменить
            </Button>
          </DrawerClose>
        </DrawerFooter>
      </DrawerContent>
    </Drawer>
  )
}
